package com.sjain.algo;

public class SystemAlgos {

    /*
    sortedMerge from n arrays
    producer-
        read x elements at a time into n ararys
        insert top 1 element+arraypos+index from each array into heap
        extract min element and put in sorted array
        get next element from the array and insert into heap
        when all arrays are exhausted, reread x elements from disk, repeat.
    consumer-
        takes elements from sorted array and serializes to disk / stream

     */

    /*
    merge sorted arrays on disk
        BufferedReader r1 = new BufferedReader(new FileReader("input1");
        BufferedReader r2 = new BufferedReader(new FileReader("input2");

        List<Integer> lres = new ArrayList<>();

        String line1, line2;
        line1 = r1.readLine();
        line2 = r2.readLine();
        while (line1 != null && line2 != null) {
            List<Integer> l1;
            List<Integer> l2;

            if (line1.trim().length() > 0) {
               l1 = Arrays.stream(line1.split("\s*")).mapToInt(Integer::parseInt).collect(Collectors::toList);
            if (line2.trim().length() > 0) {
                l2 = Arrays.stream(line2.split("\s*")).mapToInt(Integer::parseInt).collect(Collectors::toList);

            int i = 0, j = 0;
            while (i < l1.size() && j < l2.size()) {
                int a = l1.get(i);
                int b = l2.get(j);
                if (a <= b) {
                    lres.add(a);
                    i++;
                } else {
                    lres.add(b);
                    j++;
                }
             }

             for (; i < l1.size(); i++) {
                lres.add(l1.get(i));
             }

             for (; j < l2.size(); j++) {
                lres.add(l2.get(j));
             }
        }

        // read remaining file1
        while (line1 != null) {
            String[] sarr = line1.split("\s*");
            for (String s : sarr)
                lres.add(Integer.parseInt(s));
            line1 = r1.readLine();

        ]

        // read remaining file2
        while (line2 != null) {
            String[] sarr = line2.split("\s*");
            for (String s : sarr)
                lres.add(Integer.parseInt(s));
            line2 = r1.readLine();

        ]

    }

     */


    /*


     */


}
