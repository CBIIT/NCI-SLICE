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

import org.apache.commons.cli.Options;

public class CommandLineOptions {

    /**
     *
     */
    public CommandLineOptions() {
    }

    /**
     *
     * @return
     */
    protected Options createSaviALaCarteOptions() {
        Options options = new Options();
        options.addOption("b", "buildingBlocks", true, "buildingBLock in TSV file");
        options.addOption("s", "sliceRepository", true, "Folder containing SLICE chemistries");
        options.addOption("o", "outputDirectory", false, "Output directory");
        options.addOption("n", "outputFileName", false, "output FileName of the files containing the generated molecules");
        options.addOption("c", "cpu", false, "number of CPUs");
        options.addOption("l", "replace lower rating with kill", false, "replace lower rating with kill");
        options.addOption("g", "ghost molecules", false, "delete ghost molecules");
        options.addOption("v", "coompatiblebuildingBlocks", false, "count the compatible building Blocks");
        options.addOption("i", "print products with inchikey", false, "inchikey of products");
        options.addOption("h", "help", false, "Help page for command usage");
        options.addOption("m", "matching buildingBlocks", false, "count the matching building Blocks");

        return options;
    }

}
