package com.sjain.basic;

import java.util.EnumSet;

public class EnumTest {

    public enum Operation {
        VIEW_PROFILE,EDIT_PROFILE,DELETE_PROFILE,VIEW_ALBUM,EDIT_ALBUM,DELETE_ALBUM,COMMENT,RATE_PROFILE
    }

    public static void main(String[] args) {
        EnumSet<Operation> s = EnumSet.of(Operation.VIEW_PROFILE, Operation.RATE_PROFILE);
        s.add(Operation.DELETE_ALBUM);
        System.out.println(s.contains(Operation.DELETE_ALBUM));


    }
}
