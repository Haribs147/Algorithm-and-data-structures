package pl.edu.pw.ee.aisd2023zlab6.lcs;

public class LongestCommonSubsequence {
    public LongestCommonSubsequence() {
    }

    public String findLcs(String topText, String leftText) {
        int topTextSize = topText.length() + 1;
        int leftTextSize = leftText.length() + 1;
        Cell[][] matrix = new Cell[leftTextSize][topTextSize];
        for (int i = 0; i < leftTextSize; i++) {
            for (int j = 0; j < topTextSize; j++) {
                matrix[i][j] = new Cell(0, 'u');
            }
        }
        for (int i = 0; i < leftTextSize - 1; i++) {
            for (int j = 0; j < topTextSize - 1; j++) {
                if (topText.charAt(j) != leftText.charAt(i)) {
                    if (matrix[i][j + 1].getValue() < matrix[i + 1][j].getValue()) {
                        matrix[i + 1][j + 1].setArrow('l');
                        matrix[i + 1][j + 1].setValue(matrix[i + 1][j].getValue());
                    } else {
                        matrix[i + 1][j + 1].setValue(matrix[i][j + 1].getValue());
                    }
                } else {
                    matrix[i + 1][j + 1].setArrow('d');
                    matrix[i + 1][j + 1].setValue(matrix[i][j].getValue() + 1);
                }
            }
        }
        String wynik = "";
        int i = leftTextSize - 1;
        int j = topTextSize - 1;
        while (i != 0 && j != 0) {
            char move = matrix[i][j].getArrow();
            if (move == 'd') {
                i--;
                j--;
                wynik += leftText.charAt(i);
            } else if (move == 'u') {
                i--;
            } else {
                j--;
            }
        }
        String essa = new StringBuilder(wynik).reverse().toString();
        return essa;// zapoznać się z rozdziałem z cormena z rodzidziału dynamicznego
    }
}
