/*
 * Copyright (C) 2007-2018 Syed Asad Rahman <asad @ ebi.ac.uk>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.nih.main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.getProperty;
import static java.lang.System.out;

import java.util.Map;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import com.nih.tools.ColouredSystemOutPrintln;

/**
 * @contact Syed Asad Rahman, EMBL-EBI, Cambridge, UK.
 * @author Syed Asad Rahman <asad @ ebi.ac.uk>
 */
class Helper {

    static final String NEW_LINE = getProperty("line.separator");

    protected static void getHeader() {
        StringBuilder sb = new StringBuilder();

        sb.append("**************************************************");
        sb.append(NEW_LINE);
        sb.append("SLICE (Smarts and Logic In Chemistry)");
        sb.append(NEW_LINE);
        sb.append(NEW_LINE);
        sb.append("Authors: Victorien Delannee, Stefi Nouleho");
        sb.append(NEW_LINE);
        sb.append("The code was developed at the Computer-Aided Drug Design (CADD) Group");
        sb.append(NEW_LINE);
        sb.append("NCI-National Cancer Institute");
        sb.append(NEW_LINE);
        sb.append("Frederick, Maryland");
        sb.append(NEW_LINE);
        sb.append("USA");
        sb.append(NEW_LINE);
        sb.append(NEW_LINE);
        sb.append("Note: According to 17 U.S. Code § 105(a), there is no copyright on this work (CC0).");
        sb.append(NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Reference");
        sb.append(NEW_LINE);
        sb.append("<we will add the reference to your preprints/papers here as soon as we have them>");
        sb.append(NEW_LINE);
        //sb.append("doi: 10.26434/chemrxiv.12058971");
        //sb.append(NEW_LINE);
        sb.append("**************************************************");
        sb.append(NEW_LINE);
        
        out.println(sb.toString());
    }

    /**
     * WreactionWithLayoutite the preactionWithLayoutovided
     * numbereactionWithLayout of blank lineheaderString to the
     * preactionWithLayoutovided OutputStreactionWithLayouteam.
     *
     * @param numberBlankLines NumbereactionWithLayout of blank lineheaderString
     * to wreactionWithLayoutite.
     * @param out OutputStreactionWithLayouteam to which to
     * wreactionWithLayoutite the blank lineheaderString.
     */
    protected static void displayBlankLines(final int numberBlankLines, final OutputStream out) {
        try {
            for (int i = 0; i < numberBlankLines; ++i) {
                out.write(NEW_LINE.getBytes());
            }
        } catch (IOException ioEx) {
            for (int i = 0; i < numberBlankLines; ++i) {
                System.out.println();
            }
        }
    }

    /*
     System.out.println("-- USAGE --");
     printUsage(applicationName + " (Posix)", constructPosixOptions(), System.out);
     displayBlankLines(1, System.out);
     printUsage(applicationName + " (Gnu)", constructGnuOptions(), System.out);
     displayBlankLines(4, System.out);
     System.out.println("-- HELP --");
     printHelp(
     constructPosixOptions(), 80, "POSIX HELP", "End of POSIX Help",
     3, 5, true, System.out);
     displayBlankLines(1, System.out);
     printHelp(
     constructGnuOptions(), 80, "GNU HELP", "End of GNU Help",
     5, 3, true, System.out);
     */
    protected static void printHelp(final OutputStream out, final Options options) {
        final String commandLineSyntax = "java -jar ReactionCode-1.0.0.jar";
        try (PrintWriter writer = new PrintWriter(out)) {
            final HelpFormatter formatter = new HelpFormatter();
            displayBlankLines(2, out);
            formatter.printHelp(writer, 80, commandLineSyntax, "HELP",
                    options, 5, 3, "End of Helper Help", true);
            writer.flush();
            writer.close();
        }
    }

    protected static void printHelp(final Map<String, Options> optionsMap, final int printedRowWidth,
            final String header, final String footer, final int spacesBeforeOption,
            final int spacesBeforeOptionDescription, final boolean displayUsage, final OutputStream out) throws IOException {
        final String commandLineSyntax = "java -jar ReactionCode-1.0.0.jar";
        try (PrintWriter writer = new PrintWriter(out)) {
            final HelpFormatter helpFormatter = new HelpFormatter();
            //do not sort, comment next line to sort by alphabetic order
            helpFormatter.setOptionComparator(null);
            optionsMap.keySet().stream().map((headerString) -> {
                helpFormatter.printHelp(
                        writer,
                        printedRowWidth,
                        commandLineSyntax,
                        headerString,
                        optionsMap.get(headerString),
                        spacesBeforeOption,
                        spacesBeforeOptionDescription,
                        "End of Helper " + headerString + " Help",
                        displayUsage);
                return headerString;
            }).map((_item) -> {
                displayBlankLines(2, out);
                return _item;
            }).forEach((_item) -> {
                writer.flush();
            });
            writer.close();
        }
    }

    protected static void printUsageExamples() {
        StringBuilder sb = new StringBuilder();
        sb.append(NEW_LINE);
        sb.append("Encoder examples: ");
        sb.append(NEW_LINE);
        sb.append("java -jar SAVI-1.0.0-jar-with-dependencies.jar -b builingBlock.tsv -s /path/to/sliceChemistryFolder/TF_7009_V9.jslice -g");
        sb.append(NEW_LINE);
        sb.append("java -jar SAVI-1.0.0-jar-with-dependencies.jar -b builingBlock.tsv -s /path/to/sliceChemistryFolder/TF_7009_V9.jslice -v ");
        sb.append(NEW_LINE);
        out.println(sb.toString());
    }

}
