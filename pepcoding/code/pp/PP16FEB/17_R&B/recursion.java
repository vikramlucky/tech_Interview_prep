import java.util.*;

public class recursion {

    // ci-> current item, ti-> total items
    public static void permutations(int[] boxes, int ci, int ti){
        if(ci > ti) {
            // print the result
            for(int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }

        for(int b = 0; b < boxes.length; b++) {
            if(boxes[b] == 0) {
                // place
                boxes[b] = ci;
                permutations(boxes, ci + 1, ti);
                // unplace
                boxes[b] = 0;
            }
        }
    }

    // cb -> current box, tb-> total box, ssf-> selected so far, ts->total selection, asf->answer so far
    public static void combinations(int cb, int tb, int ssf, int ts, String asf){
        if(cb > tb) {
            if(ssf == ts) {
                // print
                System.out.println(asf);
            }
            return;
        }
        // yes call
        if(ssf < ts)
            combinations(cb + 1, tb, ssf + 1, ts, asf + "i");
        // no call
        combinations(cb + 1, tb, ssf, ts, asf + "-");
    }

    public static void permutations(int cb, int tb, int[] items, int ssf, int ts, String asf){
        if(cb > tb) {
            // print
            if(ssf == ts) {
                System.out.println(asf);
            }
            return;
        }

        // yes call
        for(int i = 0; i < items.length; i++) {
            if(items[i] == 0) {
                // place
                items[i] = 1;
                permutations(cb + 1, tb, items, ssf + 1, ts, asf + (i + 1));
                // unplace
                items[i] = 0;
            }
        }
        // no call
        permutations(cb + 1, tb, items, ssf, ts, asf + "-");
    }

    // lb -> last box
    public static void combinations(int[] boxes, int ci, int ti, int lb){
        if(ci > ti) {
            for(int i = 0; i < boxes.length; i++) {
                if(boxes[i] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print("i");
                }
            }
            System.out.println();
            return;
        }
        for(int b = lb + 1; b < boxes.length; b++) {
            // place
            boxes[b] = 1;
            combinations(boxes, ci + 1, ti, b);
            // unplace
            boxes[b] = 0;
        }
    }

    // Queens Combinations - 2d As 2d - Box Chooses
    // qpsf -> queen placed so far, tq -> total queen, asf -> answer so far
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf){
        if(row == tq) {
            // print result
            if(qpsf == tq) {
                System.out.println(asf);
            }
            return;
        }
        
        if(col + 1 < tq) {
            // in same row, column will increase
            // yes
            queensCombinations(qpsf + 1, tq, row, col + 1, asf + "q");
            // no
            queensCombinations(qpsf, tq, row, col + 1, asf + "-");
        } else {
            // row will increase and column begin from 0
            // yes
            queensCombinations(qpsf + 1, tq, row + 1, 0, asf + "q\n");
            // no
            queensCombinations(qpsf, tq, row + 1, 0, asf + "-\n");
        }
    }

    // Queens Permutations - 2d As 2d - Queen Chooses
    public static void queensPermutations(int qpsf, int tq, int[][] chess){
        if(qpsf == tq) {
            // print
            for(int i = 0; i < tq; i++) {
                for(int j = 0; j < tq; j++) {
                    if(chess[i][j] == 0) {
                        // empty box
                        System.out.print("-\t");
                    } else {
                        // occupied box
                        System.out.print("q" + chess[i][j] + "\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < chess.length; i++) {
            for(int j = 0; j < chess[0].length; j++) {
                if(chess[i][j] == 0) {
                    // place
                    chess[i][j] = qpsf + 1;
                    queensPermutations(qpsf + 1, tq, chess);
                    // unplace
                    chess[i][j] = 0;
                }
            }
        }
    }

    // Queens Permutations - 2d As 2d - Box Chooses
    public static void queensPermutations(int qpsf, int tq, int row, int col, String asf, boolean[] queens) {
        if(row == tq) {
            if(qpsf == tq) {
                System.out.println(asf);
                System.out.println();
            }
            return;
        }
        if(col + 1 < tq) {
            // yes call -> with loop of items
            for(int q = 0; q < queens.length; q++) {
                if(queens[q] == false) {
                    // place
                    queens[q] = true;
                    queensPermutations(qpsf + 1, tq, row, col + 1, asf + "q" + (q  + 1) + "\t", queens);
                    // unplace
                    queens[q] = false;
                }
            }

            // no call
            queensPermutations(qpsf, tq, row, col + 1, asf + "-\t", queens);
        } else {
            // yes call -> with loop of items
            for(int q = 0; q < queens.length; q++) {
                if(queens[q] == false) {
                    // place
                    queens[q] = true;
                    queensPermutations(qpsf + 1, tq, row + 1, 0, asf + "q" + (q + 1) + "\n", queens);
                    // unplace
                    queens[q] = false;
                }
            }

            // no call
            queensPermutations(qpsf, tq, row + 1, 0, asf + "-\n", queens);
        }
    }

    // Queens Combinations - 2d As 2d - Queen Chooses
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int row, int col){
        if(qpsf == tq) {
            for(int i = 0; i < tq; i++) {
                for(int j = 0; j < tq; j++) {
                    if(chess[i][j] == true) {
                        System.out.print("q\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        
        // part1
        for(int r = row, c = col + 1; c < tq; c++) {
            // mark
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, r, c);
            // unmark
            chess[r][c] = false;
        }

        // part2
        for(int r = row + 1; r < tq; r++) {
            for(int c = 0; c < tq; c++) {
                // mark
                chess[r][c] = true;
                queensCombinations(qpsf + 1, tq, chess, r, c);
                // unmark
                chess[r][c] = false;
            }
        }
    }

    // Queens Combinations - 2d As 1d - Queen Chooses
    // lcno -> last cell number used
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lcno) {
        if(qpsf == tq) {
            for(int i = 0; i < tq; i++) {
                for(int j = 0; j < tq; j++) {
                    if(chess[i][j] == true) {
                        System.out.print("q\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int box = lcno + 1; box < chess.length * chess[0].length; box++) {
            int r = box / chess[0].length;
            int c = box % chess[0].length;
            // place
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, box);
            // unplace
            chess[r][c] = false;
        }
    }

    // Nqueens Combinations - 2d As 1d - Queen Chooses
    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        // direction 0, r--, c++
        for(int r = row, c = col; r >= 0 && c < chess.length; r--, c++) {
            if(chess[r][c] == true) {
                return false;
            }
        }
        // direction 1, r--, c -> x
        for(int r = row, c = col; r >= 0; r--) {
            if(chess[r][c] == true) {
                return false;
            }
        }
        // direction 2, r--, c--
        for(int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if(chess[r][c] == true) {
                return false;
            } 
        }
        // direction 3, r -> x, c--
        for(int r = row, c = col; c >= 0; c--) {
            if(chess[r][c] == true) {
                 return false;
            }
        }

        return true;
    }

    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lcno) {
        if (qpsf == tq) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "q\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = lcno + 1; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (IsQueenSafe(chess, row, col)) {
                chess[row][col] = true;
                nqueens(qpsf + 1, tq, chess, row * chess.length + col);
                chess[row][col] = false;
            }
        }
    }

    // Nqueens Permutations - 2d As 1d - Queen Chooses
    public static boolean IsQueenSafe2(int[][] chess, int row, int col) {
        int[] xdir = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] ydir = {0, 1, 1, 1, 0, -1, -1, -1};

        for(int rad = 0; rad <= chess.length; rad++) {
            for(int d = 0; d < 8; d++) {
                int r = row + (rad * xdir[d]);
                int c = col + (rad * ydir[d]);

                if(r >= 0 && r < chess.length && c >= 0 && c < chess[0].length && chess[r][c] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void nqueens(int qpsf, int tq, int[][] chess) {
        if (qpsf == tq) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    if(chess[row][col] != 0) {
                        System.out.print("q" + chess[row][col] + "\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (IsQueenSafe2(chess, row, col)) {
                chess[row][col] = qpsf + 1;
                nqueens(qpsf + 1, tq, chess);
                chess[row][col] = 0;
            }
        }
    }

    // Permutations - Words - 1, cb-> current box, tb-> total box
    public static void generateWords(int cb, int tb, HashMap<Character, Integer> fmap, String asf) {
        if(cb > tb) {
            System.out.println(asf);
            return;
        }

        for(char ch : fmap.keySet()) {
            int freq = fmap.get(ch);
            if(freq > 0) {
                fmap.put(ch, freq - 1);
                generateWords(cb + 1, tb, fmap, asf + ch);
                fmap.put(ch, freq);
            }
        }
    }

    // Permutations - Words - 2, cci-> current character index
    public static void generateWords(int cci, String str, Character[] box, HashMap<Character, Integer> lastOccurence) {
        if(cci == str.length()) {
            for(char ch : box) {
                System.out.print(ch);
            }
            System.out.println();
            return;
        }

        char ch = str.charAt(cci);
        int li = lastOccurence.get(ch); // li -> last index
        
        for(int b = li + 1; b < box.length; b++) {
            if(box[b] == null) {
                // place character in box
                box[b] = ch;
                // set last box used in lastOccurrence
                lastOccurence.put(ch, b);

                generateWords(cci + 1, str, box, lastOccurence);

                // re-set last box used in lastOccurrence
                lastOccurence.put(ch, li);
                // unplace character in box
                box[b] = null;
            }
        }
    }

    // Words - K Selection - 1
    public static void combination(int i, String ustr, int ssf, int ts, String asf) {
        if(i == ustr.length()) {
            if(ssf == ts) {
                // print
                System.out.println(asf);
            }
            return;
        }

        char ch = ustr.charAt(i);
        // yes call
        if(ssf + 1 <= ts)
            combination(i + 1, ustr, ssf + 1, ts, asf + ch);
        // no call
        combination(i + 1, ustr, ssf, ts, asf);
    }

    // Words - K Selection - 2
    public static void combination(String ustr, int ssf, int ts, int li, String asf) {
        if(ssf == ts) {
            System.out.println(asf);
            return;
        }

        for(int i = li + 1; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            combination(ustr, ssf + 1, ts, i, asf + ch);
        }
    }

    // Words - K Length Words - 1
    public static void permutation(String ustr, int indx, int ssf, Character[] slots) {
        if(ssf == slots.length) {
            // print
            for(char ch : slots) {
                System.out.print(ch);
            }
            System.out.println();
            return;
        }
        if(indx == ustr.length()) return;
        
        char ch = ustr.charAt(indx);
        // yes call
        for(int s = 0; s < slots.length; s++) {
            if(slots[s] == null) {
                // place
                slots[s] = ch;
                permutation(ustr, indx + 1, ssf + 1, slots);
                // unplace
                slots[s] = null;
            }
        }
        // no call
        permutation(ustr, indx + 1, ssf, slots);
    }

    // Words - K Length Words - 2, cs-> current slot, ts-> total slot
    public static void permutation(String ustr, int cs, int ts, String asf, HashSet<Character> vis) {
        if(cs == ts) {
            System.out.println(asf);
            return;
        }
        for(int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            if(!vis.contains(ch)) {
                // add in visited
                vis.add(ch);
                permutation(ustr, cs + 1, ts, asf + ch, vis);
                // remove from visited
                vis.remove(ch);
            }
        }
    }

    // Words - K Selection - 3
    public static void words_K_Selection3(String ustr, int indx, HashMap<Character, Integer> fmap, String asf, int k) {
        if(asf.length() == k) {
            System.out.println(asf);
            return;
        }
        if(indx == ustr.length()) return;
        
        char ch = ustr.charAt(indx);
        int freq = fmap.get(ch);
        // yes call
        int len = asf.length();
        for(int i = freq; i > 0; i--) {
            if(len + i <= k) {
                String str = "";
                for(int j = 0; j < i; j++) {
                    str += ch;
                }
                words_K_Selection3(ustr, indx + 1, fmap, asf + str, k);
            }
        }
        // no call
        words_K_Selection3(ustr, indx + 1, fmap, asf, k);
    }

    // Words - K Selection - 4
    public static void words_K_Selection4(String ustr, int cs, int ts, String asf, int li,
        HashMap<Character, Integer> fmap) {
        if(cs == ts) {
            System.out.println(asf);
            return;
        }
        for(int i = li; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if(freq > 0) {
                // freq reduce
                fmap.put(ch, freq - 1);
                words_K_Selection4(ustr, cs + 1, ts, asf + ch, i, fmap);
                // freq regain
                fmap.put(ch, freq);
            }
        } 
    }

    // Words - K Length Words - 3
    public static void words_K_Length_Words3(String str, int indx, Character[] slots, 
        HashMap<Character, Integer> lastIndx, int ssf) {
        if(indx == str.length()) {
            if(ssf == slots.length) {
                for(char c : slots) {
                    System.out.print(c);
                }
                System.out.println();
            }
            return;
        }
        
        char ch = str.charAt(indx);
        int li = lastIndx.get(ch);
        
        // yes call
        for(int s = li + 1; s < slots.length; s++) {
            if(slots[s] == null) {
                // place in slots
                slots[s] = ch;
                // update last index
                lastIndx.put(ch, s);
                words_K_Length_Words3(str, indx + 1, slots, lastIndx, ssf + 1);
                // reset l;;ast index 
                lastIndx.put(ch, li);
                // unplace in slot
                slots[s] = null;
            }
        }
        // no call
        if(li == -1) {
            words_K_Length_Words3(str, indx + 1, slots, lastIndx, ssf);
        }
    }

    // Words - K Length Words - 4
    public static void words_K_Length_Words4(String ustr, int cs, int ts, String asf, HashMap<Character, Integer> fmap) {
        if(cs == ts) {
            System.out.println(asf);
            return;
        }
        
        for(int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if(freq > 0) {
                // reduce freq
                fmap.put(ch, freq - 1);
                words_K_Length_Words4(ustr, cs + 1, ts, asf + ch, fmap);
                // gain freq
                fmap.put(ch, freq);
            }
        }
    }


    public static void fun() {

    }

    public static void main(String[] args) {
        fun();    
    }
}