package de.nightmoon.boompixel;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jo-Jo-Computer on 11.06.2017.
 */
public class VoidWorldGenerator extends ChunkGenerator {

    @Override
    public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        return new byte[0][0];
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return new LinkedList<BlockPopulator>();
    }
}
