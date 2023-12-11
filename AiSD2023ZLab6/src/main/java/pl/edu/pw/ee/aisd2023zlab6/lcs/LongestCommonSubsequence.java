package pl.edu.pw.ee.aisd2023zlab6.lcs;

public class LongestCommonSubsequence {
    public LongestCommonSubsequence(){
    }
    public String findLcs(String topText, String leftText) {
        int topTextSize = topText.length()+1;
        int leftTextSize = leftText.length()+1;
        Cell[][] matrix = new Cell [topTextSize][leftTextSize];
        for (int i = 0; i < leftTextSize; i++){
            for(int j = 0; j < topTextSize; j++){
                matrix[i][j] = new Cell(0,'u');
            }
        }
        for (int i = 0; i < leftTextSize-1; i++){
            for(int j = 0; j < topTextSize-1; j++){
                if(topText.charAt(i) != leftText.charAt(j)){
                     if(matrix[i][0].getValue() > matrix[0][j].getValue()){
                         matrix[i+1][j+1].setArrow('l');
                         matrix[i+1][j+1].setValue(matrix[i+1][j].getValue());
                     }
                     else{
                         matrix[i+1][j+1].setValue(matrix[i][j+1].getValue());
                     }
                }
                else{
                    matrix[i+1][j+1].setArrow('d');
                    matrix[i+1][j+1].setValue(matrix[i][j].getValue()+1);
                }
            }
        }
        //zamienilem leftTextSize z TopTextSize miejscami
        for (int i = 0; i < leftTextSize; i++){
            for(int j = 0; j < topTextSize; j++){
                System.out.print(matrix[i][j].getValue() + " ");
            }
            System.out.println();
        }
        
        return null;// zapoznać się z rozdziałem z cormena z rodzidziału dynamicznego
    }

}
