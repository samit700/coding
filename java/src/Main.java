import com.sjain.algo.DP;
import com.sjain.algo.General;
import com.sjain.algo.Stats;
import com.sjain.algo.StringAlgos;
import com.sjain.concurrency.ConditionTest;
import com.sjain.ds.ListAlgos;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {

        /*
        Stack<Integer> s = new Stack<Integer>();
        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println(s.size());

        System.out.println(s.pop());

        System.out.println(s);
        */

        /*
        CircularQueue<Integer> q = new CircularQueue<Integer>();
        q.offer(100);
        q.offer(200);
        System.out.println(q.size());
        System.out.println(q.poll());
        System.out.println(q.contains(400));
        System.out.println(q.size());
        System.out.println(q.contains(200));
        System.out.println(q.poll());
        System.out.println(q.isEmpty());
        */


        /*
        System.out.println(Arrays.toString(
                ListAlgos.calculateSpan(new int[] {100, 80, 60, 70, 60, 75, 85}, 7)));

        System.out.println(ListAlgos.maxSubArraySum(new int[] {100, 80, 60, 70, 60, 175, 85}, 7, 3));

        System.out.println(Arrays.toString(ListAlgos.maxSubArrays(new int[] {100, 80, 60, 70, 60, 175, 85}, 7, 3)));
        */

        /*
        BinaryTree<Character> tree = new BinaryTree<Character>();
        BinaryTree.Node<Character> root = new BinaryTree.Node<Character>('a');
        tree.root = root;
        root.left = new BinaryTree.Node<Character>('b');
        root.right = new BinaryTree.Node<Character>('c');
        root.right.left = new BinaryTree.Node<Character>('d');
        root.right.right = new BinaryTree.Node<Character>('e');
        root.right.right.right = new BinaryTree.Node<Character>('f');

        tree.bfs();
        System.out.println();
        tree.dfs();
        System.out.println();
        tree.printInOrder();
        System.out.println();

        System.out.println(TreeAlgos.isUnivalTree(root));
        System.out.println(TreeAlgos.isUnivalTree(root.left));
        System.out.println(TreeAlgos.isUnivalTree(root.right));
        System.out.println(TreeAlgos.countUnivalTree(root));
        */

        /*
        BinaryTree<Character> tree = new BinaryTree<Character>();
        BinaryTree.Node<Character> root = new BinaryTree.Node<Character>('a');
        tree.root = root;
        root.left = new BinaryTree.Node<Character>('b');
        root.right = new BinaryTree.Node<Character>('c');
        root.left.left = new BinaryTree.Node<Character>('g');
        root.left.right = new BinaryTree.Node<Character>('h');
        root.right.left = new BinaryTree.Node<Character>('d');
        root.right.right = new BinaryTree.Node<Character>('e');
        root.right.right.right = new BinaryTree.Node<Character>('f');

        tree.bfs();
        System.out.println();
        System.out.println(TreeAlgos.depth(root, 'f'));
        System.out.println(TreeAlgos.depth(root, 'x'));

        System.out.println(TreeAlgos.distance(root, 'a', 'a'));
        System.out.println(TreeAlgos.distance(root, 'a', 'x'));
        System.out.println(TreeAlgos.distance(root, 'g', 'h'));
        System.out.println(TreeAlgos.distance(root, 'b', 'c'));
        System.out.println(TreeAlgos.distance(root, 'g', 'f'));
        System.out.println(TreeAlgos.distance(root, 'c', 'f'));
        System.out.println(TreeAlgos.distance(root, 'a', 'h'));
        */

        /*
        long startTime, endTime;

        startTime = System.currentTimeMillis();
        System.out.println(TreeAlgos.stepsPermutation(1000));
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        System.out.println(TreeAlgos.stepsPermutationIter(1000));
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        System.out.println(DP.stepsPermutation(1000));
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        System.out.println(TreeAlgos.stepsPermutation2(1000));
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        */

        /*
        BinaryTree<Character> tree = new BinaryTree<Character>();
        BinaryTree.Node<Character> root = new BinaryTree.Node<Character>('a');
        tree.root = root;
        root.left = new BinaryTree.Node<Character>('b');
        root.right = new BinaryTree.Node<Character>('c');
        root.right.left = new BinaryTree.Node<Character>('d');
        root.right.right = new BinaryTree.Node<Character>('e');
        root.right.right.right = new BinaryTree.Node<Character>('f');

        System.out.println(TreeAlgos.getAncestors(root, 'd'));
        */

        /*
        Graph<Integer> g = new Graph<>();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println(g);

        g.dfs(2);
        System.out.println(g.isCyclic());

        System.out.println(GraphAlgos.distance(g,0,2));
        */

        /*
        System.out.println(Arrays.toString(ListAlgos.reverse(new Integer[] {1, 2, 3, 4, 5})));

        Set<Integer> s = new HashSet<Integer>();
        Collection<Number> s2 = Collections.unmodifiableCollection(s);

        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5);
        List<Number> l2 = new ArrayList<>();
        ListAlgos.copyList(l, l2);
        System.out.println(l2);
        */

        /*
        int[] arr = new int[] {1,43,43,1111,43893949,9, 2, 20};
        System.out.println(ListAlgos.maxDigits(arr));

        ListAlgos.radixSort(arr);
        System.out.println(Arrays.toString(arr));
        */

        /*
        int[] arr2 = new int[] {2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8};
        ListAlgos.sortByFrequency(arr2);
        System.out.println(Arrays.toString(arr2));
        */

        /*
        int[][] m = new int[][]
                {
                        {1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12},
                        {13,14,15,16}
                };
        System.out.println(MatrixAlgo.index(m,4,6));

        System.out.println(MatrixAlgo.binarySearch(m,4,7));

        MatrixAlgo.print(m,4,4);
        MatrixAlgo.setRowColOnes(m,4,4);
        MatrixAlgo.print(m,4,4);
        */

        /*
        int[][] m = new int[][]
                {
                        {1,2,3,4,5},
                        {6,7,8,9,10},
                        {11,12,13,14,15},
                        {16,17,18,19,20},
                        {21,22,23,24,25}
                };
        MatrixAlgo.print(m,5,5);
        System.out.println();
        MatrixAlgo.transpose(m, 5);
        MatrixAlgo.print(m,5,5);
        */

        /*
        MatrixAlgo.printSpiral(m, 5, 5);
        */

        /*
        int[] a = new int[] {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(ListAlgos.maxSubArraySum(a));
        System.out.println(ListAlgos.maxSubArraySum(new int[] {10,-20,100,2,-1}));
        /*

        /*
        int[][] m = new int[][]
                {
                    {1, 2, -1, -4, -20},
                    {-8, -3, 4, 2, 1},
                    {3, 8, 10, 1, 3},
                    {-4, -1, 1, 7, -6}
                };
        System.out.println(MatrixAlgo.maxRectangeSum(m, 4));
        */

        /*
        MinHeap h = new MinHeap(20);
        h.push(3);
        h.push(2);


        System.out.println(h);

        System.out.println(h.pop());
        h.push(15);
        h.push(5);
        h.push(4);
        h.push(45);

        System.out.println(h);
        System.out.println(h.pop());
        System.out.println(h);
        */

        /*
        int[][] arr= {{2, 6, 12, 34},
                {1, 9, 20, 1000},
                {23, 34, 90, 2000}};

        int[] sarr = MatrixAlgo.mergeKSortedArrays(arr, 3, 4);
        System.out.println(Arrays.toString(sarr));
        */

        /*
        int[][] m =
        {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        MatrixAlgo.print(m, 4,4);
        //MatrixAlgo.rotate90(m, 4, true);
        MatrixAlgo.rotate90InPlace(m, 4, false);
        System.out.println();
        MatrixAlgo.print(m, 4,4);
        */

        /*
        int[][] m =
                {
                        {1, 0, 1, 0, 0},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 1, 0}
                };
        System.out.println(MatrixAlgo.countRegions(m, 4, 5));
        System.out.println(MatrixAlgo.countRegionsIterative(m, 4, 5));

        int[][] m2 =
                {
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1}
                };
        System.out.println(MatrixAlgo.countRegions(m2, 4, 5));
        System.out.println(MatrixAlgo.countRegionsIterative(m2, 4, 5));
        */

        /*
        System.out.println(DP.longestPalindromeContinuous(""));
        System.out.println(DP.longestPalindromeContinuous(null));
        System.out.println(DP.longestPalindromeContinuous("a"));
        System.out.println(DP.longestPalindromeContinuous("abcddcee"));
        System.out.println(DP.longestPalindromeContinuous("abcdedcbeeee"));
        System.out.println(DP.longestPalindromeContinuous("BBABCBCAB"));

        System.out.println(DP.longestPalindromeNonCont1("BBABCBCAB", 0, 8));

        System.out.println(DP.longestPalindromeNonCont2("BBABCBCAB", 0, 8));
        System.out.println(DP.longestPalindromeNonCont2("geeksforgeeks", 0, 12));

        System.out.println(DP.longestPalindromeContinuous("abcddcee"));
        System.out.println(DP.longestPalindromeContinuous("abcdedcbeeee"));
        System.out.println(DP.longestPalindromeCont1("BBABCBCAB", 0, 8));
        */

        /*
        String[] alpha = {"a","c","t"};
        System.out.println(General.wordsInOrder(Arrays.asList(alpha), Arrays.asList(new String[] {"cat", "act", "cat"})));
        System.out.println(General.wordsInOrder(Arrays.asList(alpha), Arrays.asList(new String[] {"act", "actb", "cat"})));
        */

        /*
        System.out.println(General.checkSubsetSum(8, new int[] {1,9,4,4}));
        System.out.println(General.checkSubsetSum(8, new int[] {1,9,4,5}));
         */

        /*
        Character[] alpha = {'a','c','t', 's', 'x'};
        System.out.println(General.sortWord(Arrays.asList(alpha), "catactattxaac"));

        List<String> words = new ArrayList<String>(Arrays.asList(new String[] {"cat", "act", "cat", "acts", "cta", "catax"}));
        General.sortWords(Arrays.asList(alpha), words);
        System.out.println(words);
        */


        /*
        System.out.println(DP.longestIncreasingSeqRecur(new int[] {5,7,18,6,4,8,9,10}, 8));
        System.out.println(DP.longestIncreasingSeqRecur(new int[] {3, 10, 2, 1, 20}, 5));
        System.out.println(DP.longestIncreasingSeqRecur(new int[] {50, 3, 10, 7, 40, 80}, 6));

        System.out.println(DP.longestIncreasingSeq(new int[] {5,7,18,6,4,8,9,10}, 8));
        System.out.println(DP.longestIncreasingSeq(new int[] {3, 10, 2, 1, 20}, 5));
        System.out.println(DP.longestIncreasingSeq(new int[] {50, 3, 10, 7, 40, 80}, 6));
        */

        /*
        System.out.println(Binary.countBits(9));
        System.out.println(Binary.countBits(-9));
        */

        /*
        char[][] parenGram = new char[][]
                {
                    {'{','}'},
                    {'[',']'},
                    {'(',')'}
                };
        Parenthesis paren = new Parenthesis(parenGram);
        System.out.println(General.checkBalancedParens("", paren));
        System.out.println(General.checkBalancedParens("[(])", paren));
        System.out.println(General.checkBalancedParens("[()]{}{[()()]()}", paren));
        System.out.println(General.checkBalancedParens("([])", paren));
        */

        /*
        System.out.println(Arrays.toString(General.stockSpan(new int[] {100, 80, 60, 60, 70, 60, 75, 85}, 8)));
        */

        /*
        String[] words = {"g", "ge", "gf", "geek", "gfor", "gforgeeks" , "geeksquiz"};
        General.printWordsMatchingPrefix(words, "ge");
        System.out.println();

        String[] words2 = {
                "mobile","samsung","sam",
                "sung","man","mango",
                "icecream","and","go","i",
                "like","ice","cream"};
        System.out.println(General.wordBreak(words2, "like"));
        System.out.println(General.wordBreak(words2, "ilike"));
        System.out.println(General.wordBreak(words2, "ilikelikeimangoiii"));
        System.out.println(General.wordBreak(words2, "ilik"));
        */

        /*
        String[] words2 = {
                "mobile","samsung","sam",
                "sung","man","mango",
                "icecream","and","go","i",
                "like","ice","cream"};
        CharacterTrie dict = new CharacterTrie();
        for (String word : words2)
            dict.insert(word);

        System.out.println(dict.isValidWord("icecream"));
        System.out.println(dict.isValidWord("icecreaml"));
        List<List<String>> l = dict.wordBreakWords("icecream");
        */

        /*
        System.out.println(General.maxNumericValue("01231", 0, 5));
        System.out.println(General.maxNumericValue("123151", 0, 6));
        */

        //System.out.println(2==2?3==3?6:7:4==4?8:9);
        /*
        General.ternaryExprTree("a?b:c").bfs();
        General.ternaryExprTree("a?b?c:d:e?f:g").bfs();
        */

        //System.out.println(Arrays.toString(General.sort(new int[] {5,3,4,7,1,23,1,9,100,4,2,5,1111,0,-1}, 0, 14)));

       // System.out.println(General.minInsertsForPalin("abxccba"));

        /*
        Stats.Interval[] intervals = new Stats.Interval[]
                {
                        new Stats.Interval(1, 3, 3.5),
                        new Stats.Interval(2, 5, 2.5),
                        new Stats.Interval(2, 5, 2.0),
                        new Stats.Interval(4, 8, 4.0),
                        new Stats.Interval(7, 8, 3.0),
                        new Stats.Interval(10, 12, 1.0)
                };
        System.out.println(Stats.getLowestPriceIntervals(Arrays.asList(intervals)));
        */

        /*
        String[] dict = new String[] {"goat", "guy", "goal", "game", "loag", "agol"};
        System.out.println(General.findMatchingWords(dict, "algo"));
        */

        //General.printPermutations("ABCDE");


        /*
        int[][] m = new int[][]
                {
                        {1,2,3,4,5},
                        {6,7,8,9,10},
                        {11,12,13,14,15},
                        {16,17,18,19,20},
                        {21,22,23,24,25}
                };
        MatrixAlgo.rotateK(m, 5, 5, 7);
        */

        /*
        System.out.println(General.getNumString(4));
        System.out.println(General.getNumString(43));
        System.out.println(General.getNumString(443));
        System.out.println(General.getNumString(4443));
        */

        //System.out.println(Stats.addOne(new int[] {9, 8, 9, 9}));
        //System.out.println(Arrays.toString(General.moveZeros(new int[] {0,1,0,3,12})));

        //System.out.println(General.reverseInt(-19384));

        //System.out.println(General.isPalindrome("A man, a plan, a canal: Panama"));

        //System.out.println(General.subString("aaabaa", "ab"));

        /*
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        BinaryTree.Node<Integer> root = new BinaryTree.Node<Integer>(5);
        tree.root = root;
        root.left = new BinaryTree.Node<>(3);
        root.right = new BinaryTree.Node<>(7);
        root.right.left = new BinaryTree.Node<>(6);
        root.right.right = new BinaryTree.Node<>(8);
        root.right.right.right = new BinaryTree.Node<>(10);

        System.out.println(TreeAlgos.isBfs(root));
        */

        /*
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        BinaryTree.Node<Integer> root = new BinaryTree.Node<Integer>(5);
        tree.root = root;
        root.left = new BinaryTree.Node<>(3);
        root.right = new BinaryTree.Node<>(7);
        root.left.left = new BinaryTree.Node<>(6);
        root.left.right = new BinaryTree.Node<>(8);
        root.right.left = new BinaryTree.Node<>(10);
        root.right.right = new BinaryTree.Node<>(10);
        //root.right.left.left = new BinaryTree.Node<>(10);

        System.out.println(TreeAlgos.isBalancedTree(root));
        System.out.println(TreeAlgos.isBalancedTreeIter(root));
        */

        /*
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        BinaryTree.Node<Integer> root = new BinaryTree.Node<Integer>(1);
        tree.root = root;
        root.left = new BinaryTree.Node<>(2);
        root.right = new BinaryTree.Node<>(3);
        root.left.left = new BinaryTree.Node<>(4);
        root.left.right = new BinaryTree.Node<>(5);
        root.left.left.left = new BinaryTree.Node<>(7);

        root.right.left = new BinaryTree.Node<>(2);
        root.right.right = new BinaryTree.Node<>(6);
        root.right.right.right = new BinaryTree.Node<>(8);

        System.out.println(TreeAlgos.deepestLeavesSum(root));
        */


        //System.out.println(DP.maximizeTheft(new int[] {1, 3, 6, 1, 1, 100}));

        /*
        SecurityPriceCache cache = new SecurityPriceCache(100);
        cache.updatePrice("IBM", 98.4);
        cache.updatePrice("GOOG", 543.5);

        System.out.println(cache);

        FileOutputStream fos= new FileOutputStream("C:\\tmp\\myfile");
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(cache);
        oos.close();
        fos.close();


        FileInputStream fis = new FileInputStream("C:\\tmp\\myfile");
        ObjectInputStream ois = new ObjectInputStream(fis);
        cache = (SecurityPriceCache) ois.readObject();
        ois.close();
        fis.close();
        System.out.println(cache);
        */

        //System.out.println(General.braceExpansion("{a,b,c}"));
        //System.out.println(General.braceExpansion("{a,b}{c,d}"));
        //System.out.println(General.braceExpansion("a{b,c}{d,e}f{g,h}"));


        //System.out.println(General.chunkedPalindrome("abcdefdefabc"));
        //System.out.println(General.chunkedPalindrome("merchant"));
        //System.out.println(General.chunkedPalindrome("ghiabcdefhelloadamhelloabcdefghi"));

        //System.out.println(General.canTransformWithReplaces("a","b"));
        //System.out.println(General.canTransformWithReplaces("afdff","boigjg"));
        //System.out.println(General.canTransformWithReplaces("abcdefghijklmnopqrstuvwxyz","cbadefghijklmnopqrstuvwxyz"));

                /*
        Integer[] iarr = {10,5,7,2,45,3};
        LambdaTest.lambdaCompare(iarr);
        System.out.println(Arrays.toString(iarr));

        LambdaTest.lambdaRunnable().join();

        LambdaTest.testCustomPredicate();

        System.out.println(LambdaTest.testCompose());

        LambdaTest.testLambdaPrimitives();

        LambdaTest.testSupplier();

        LambdaTest.testConsumer();

        LambdaTest.testMethodReference();
        */

        /*
        StreamsTest.toCollection();
        StreamsTest.toMap();
        StreamsTest.chain();
        StreamsTest.stats();
        StreamsTest.grouping();
        StreamsTest.customCollector();
        */

        /*
        System.out.println(General.maxProfit(new double[] {4.5, 5.5, 3.9, 6.8, 1.0}));
        System.out.println(General.maxProfit(new double[] {1.5, 5.5, 3.9, 6.8, 1.0}));

        System.out.println(General.maxSpan(new double[] {4.5, 5.5, 3.9, 6.8, 1.0}));
        System.out.println(General.maxSpan(new double[] {1.5, 5.5, 3.9, 6.8, 1.0}));
        */

        //ProducerConsumer.test();
        //LockTest.test();
        //ConditionTest.test();

        //System.out.println(General.maxSubsetSum(new int[] {3, 34, 4, 12, 5, 2}, 9));
        //System.out.println(DP.coinChangeCount(new int[] {3, 34, 4, 12, 5, 2, 7}, 9));  // 5
        //System.out.println(DP.subsetSumCount(new int[] {3, 34, 4, 12, 5, 2, 7}, 9)); // 2

        //System.out.println(ListAlgos.binarySearch(new int[] {1, 3, 5,6, 8,11}, 8));

        /*
        int[] arr = {1,2,3,4,5};
        int[] barr = {3,5};
        System.out.println(DP.longestCommonSubseqRecur(arr,barr,0,0,0));
        System.out.println(DP.longestCommonSubseq(arr,barr,0,0,0));
         */

        //System.out.println(General.isPalindromeOnlyAlpha("?ma_m_?....!"));

        /*
        String s = "ababbbabbababa";
        //s = "abbac";
        System.out.println(DP.minPalindromeSplits(s, 0, s.length()-1));
        System.out.println(DP.minPalindromeSplitsDP(s));
         */

        /*
        Character[] alpha = {'a','c','t', 'z'};
        System.out.println(General.sentenceInOrder(Arrays.asList(alpha),
                "act cat cat cattt zat"));
         */

        /*
        System.out.println(Stats.addBigNumbers("-123","-120"));
        System.out.println(Stats.addBigNumbers("999","1"));
        System.out.println(Stats.addBigNumbers("-1000","1"));

        System.out.println(Stats.addBigNumbers("1000","599"));
        System.out.println(Stats.addBigNumbers("-1000","599"));
        System.out.println(Stats.addBigNumbers("-1000","-599"));
        System.out.println(Stats.addBigNumbers("1000","-599"));
        */

        System.out.println(StringAlgos.getLinesNeededForText("this is a text", 4));
        //System.out.println(StringAlgos.getLinesNeededForText("this is a text", 3));
        System.out.println(StringAlgos.getLinesNeededForText("  this is a a dsd kdsj", 5));


    }


}
