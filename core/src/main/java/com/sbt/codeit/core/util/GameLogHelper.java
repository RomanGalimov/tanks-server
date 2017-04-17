package com.sbt.codeit.core.util;

import com.sbt.codeit.core.model.Tank;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-selin-an on 17.04.2017.
 */
public class GameLogHelper {

    private Tank first;
    private Tank second;
    private PrintWriter writer;
    private static String path = "winners.txt";

    public void setFirst(Tank first) {
        this.first = first;
    }

    public void setSecond(Tank second) {
        this.second = second;
    }

    public Tank getFirst() {
        return first;
    }

    public Tank getSecond() {
        return second;
    }

    public void write(String text) {
        try {
            if (first != null && second != null) {
                OutputStream outputStream = new FileOutputStream(getFirst().getName() + "_" + getSecond().getName() + ".txt", true);
                writer = new PrintWriter(outputStream, true);
                writer.println(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeWinner(Tank winner, Tank looser) {
        try {
            OutputStream outputStream = new FileOutputStream(path, true);
            writer = new PrintWriter(outputStream, true);
            writer.println(winner.getName() + "=3");
            writer.println(looser.getName() + "=0");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeDeadHeat(Tank t) {
        try {
            OutputStream outputStream = new FileOutputStream(path, true);
            writer = new PrintWriter(outputStream, true);
            writer.println(t.getName() + "=1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeTimeoutWinner() {
        if(first.getHits() > second.getHits()){
            write(String.format("Game stopped by timeout. Winner is '%1$s' (%2$s hits) over '%3$s' (%4$s hits)",
                    first.getName(), first.getHits(),
                    second.getName(), second.getHits()));
            writeWinner(first, second);
        }
        if(first.getHits() < second.getHits()){
            write(String.format("Game stopped by timeout. Winner is '%1$s' (%2$s hits) over '%3$s' (%4$s hits)",
                    second.getName(), second.getHits(),
                    first.getName(), first.getHits()));
            writeWinner(second, first);
        }
        if(first.getHits() == second.getHits()){
            write(String.format("No winner. Dead heat between '%1$s' (%2$s hits) and '%3$s' (%4$s hits)",
                    first.getName(), first.getHits(),
                    second.getName(), second.getHits()));
            writeDeadHeat(first);
            writeDeadHeat(second);
        }
    }

    public void writeField(ArrayList<ArrayList<Character>> field) {
        for (List l : field){
            write(l.toString());
        }
        write("=============================================================================");
    }
}