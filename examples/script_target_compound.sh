#!/bin/bash

# Check if all 5 parameters are given
if [ "$#" -ne 5 ]; then
    echo "Usage: $0 <file_reactant1> <file_reactant2> <slicefile> <jar_path> <Number_of_product_per_file>"
    exit 1
fi


# Assign input arguments
FILE_REACTANTS_1="$1"
FILE_REACTANTS_2="$2"
SLICEFILE="$3"
JAR_PATH="$4"
MAX_PRODUCTS="$5"

echo " FILE REACTANT 1: $FILE_REACTANTS_1"
echo " FILE REACTANT 1: $FILE_REACTANTS_2"
echo " JSLICE FILE : $SLICEFILE"
echo " ENGINE JAR $JAR_PATH"
echo " NUMBER OF PRODUCTS MAX PER FILE $MAX_PRODUCTS"
# Output directory will be in the same folder as the slice file
OUTPUT_DIR=$(dirname "$SLICEFILE")

echo "OUTPUT WILL BE : $OUTPUT_DIR"


# Extract base slice name (without extension) and transformation ID
BASE_SLICE=$(basename "$SLICEFILE" .jslice)         # e.g., TF_7009_v8
TFID=$(echo "$BASE_SLICE" | sed -E 's/^TF_([0-9]+).*/\1/')  # extracts 7009

# Create a file with both reactants
echo -e "SMILES\tSTRID" > "$OUTPUT_DIR/reactants.txt"
awk '{print $0 "\t0"}' "$FILE_REACTANTS_1" >> "$OUTPUT_DIR/reactants.txt"
awk '{print $0 "\t1"}' "$FILE_REACTANTS_2" >> "$OUTPUT_DIR/reactants.txt"

# Input file with all BBs
BBS="$OUTPUT_DIR/reactants.txt"
echo "Start of program"
echo "Compatible BBs computation: $BBS $SLICEFILE"
java -jar "$JAR_PATH" -b "$BBS" -s "$SLICEFILE" -v


# Define output reactants using TFID and base slice name
REACTANT1="$OUTPUT_DIR/${TFID}_compatibles_BB_0_0_results_${BASE_SLICE}_reactants.txt"
REACTANT2="$OUTPUT_DIR/${TFID}_compatibles_BB_0_1_results_${BASE_SLICE}_reactants.txt"

# Get base filename (used for naming chunks)
BASE1=$(basename "$REACTANT1" .txt)

# Count lines
NUM_REACTANT_1=$(wc -l < "$REACTANT1")
NUM_REACTANT_2=$(wc -l < "$REACTANT2")

echo "Number of reactant 1: $NUM_REACTANT_1"
echo "Number of reactant 2: $NUM_REACTANT_2"


# Chunking logic
if (( NUM_REACTANT_1 * NUM_REACTANT_2 <= MAX_PRODUCTS )); then
    COMBINED="combined_reactants.txt"
    printf "SMILES\tSTRID\n" > "$COMBINED"
    cat "$REACTANT1" "$REACTANT2" >> "$COMBINED"
    echo "Running all BBs in one chunk"
    java -jar "$JAR_PATH" -b "$COMBINED" -s "$SLICEFILE" -g
else
    if (( NUM_REACTANT_1 >= NUM_REACTANT_2 )); then
        MAX_R1_CHUNK=$((MAX_PRODUCTS / NUM_REACTANT_2))
        echo "Splitting $REACTANT1 into chunks of $MAX_R1_CHUNK lines ..."
        split -l "$MAX_R1_CHUNK" "$REACTANT1" "$OUTPUT_DIR/${BASE1}_reactant1_chunk_"

        for CHUNK in "$OUTPUT_DIR"/${BASE1}_reactant1_chunk_*; do
            COMBINED="combined_$(basename "$CHUNK").txt"
            printf "SMILES\tSTRID\n" > "$COMBINED"
            cat "$CHUNK" "$REACTANT2" >> "$COMBINED"
            echo "Running chunk: $(basename "$CHUNK")"
            java -jar "$JAR_PATH" -b "$COMBINED" -s "$SLICEFILE" -g
        done

        rm "$OUTPUT_DIR"/${BASE1}_reactant1_chunk_*
    else
        MAX_R2_CHUNK=$((MAX_PRODUCTS / NUM_REACTANT_1))
        echo "Splitting $REACTANT2 into chunks of $MAX_R2_CHUNK lines ..."
        split -l "$MAX_R2_CHUNK" "$REACTANT2" "$OUTPUT_DIR/${BASE1}_reactant2_chunk_"

        for CHUNK in "$OUTPUT_DIR"/${BASE1}_reactant2_chunk_*; do
            COMBINED="combined_$(basename "$CHUNK").txt"
            printf "SMILES\tSTRID\n" > "$COMBINED"
            cat "$REACTANT1" "$CHUNK" >> "$COMBINED"
            echo "Running chunk: $(basename "$CHUNK")"
            java -jar "$JAR_PATH" -b "$COMBINED" -s "$SLICEFILE" -g
        done

        rm "$OUTPUT_DIR"/${BASE1}_reactant2_chunk_*
    fi
fi

echo "All chunks processed."

