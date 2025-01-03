package com.victor.kochnev.a.filters;

import java.io.EOFException;
import java.util.LinkedList;

public class Pipe {
    private final LinkedList<String> buffer = new LinkedList<String>();
    private boolean closed = false;


    public void write(String s) {
        if (closed)
            return;
        buffer.add(s);
    }

    public String read() throws EOFException {
        while (true) {
            if (buffer.isEmpty()) {
                if (closed)
                    throw new EOFException();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.err.println("InterruptedExcpetion caught in Pipe");
                }
            } else {
                return buffer.pop();
            }
        }
    }

    public void close() {
        closed = true;
    }

}
