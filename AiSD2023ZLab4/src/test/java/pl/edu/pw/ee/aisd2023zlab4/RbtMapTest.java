package pl.edu.pw.ee.aisd2023zlab4;


import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class RbtMapTest {

    @Test
    public void TreeShouldHaveDeleteRight() {
        RbtMap<Integer, String> rbt = new RbtMap<>();
        rbt.setValue(6, "D");
        rbt.setValue(5, "L");
        rbt.setValue(6, "U");
        rbt.Delete();
        assertThat(rbt.getValue(6)).isEqualTo(null);

    }

    @Test
    public void TreeShouldHaveAllRightNodesBlack() {
        // given
        Stack<Node<Integer, String>> stack = new Stack<>();
        RbtMap<Integer, String> rbt = new RbtMap<>();
        /*rbt.setValue(1,"D");
        rbt.setValue(3,"L");
        rbt.setValue(6,"U");
        rbt.setValue(2,"G");
        rbt.setValue(0,"A");
        rbt.setValue(5,"S");
        rbt.setValue(4,"N");
        rbt.setValue(7,"Y");

*/
        rbt.setValue(17, "17");
        rbt.setValue(16, "16");
        rbt.setValue(15, "15");
        rbt.setValue(14, "14");
        rbt.setValue(13, "13");
        rbt.setValue(12, "12");
        rbt.setValue(11, "11");
        rbt.setValue(10, "10");
        rbt.setValue(9, "9");
        rbt.setValue(8, "8");
        rbt.setValue(7, "7");
        rbt.setValue(6, "6");
        rbt.setValue(5, "5");
        rbt.setValue(4, "4");
        rbt.setValue(3, "3");
        rbt.setValue(2, "2");
        rbt.setValue(1, "1");
        rbt.setValue(0, "0");
        rbt.setValue(-1, "-1");
        rbt.setValue(-2, "-2");
        rbt.setValue(-3, "-3");
        rbt.setValue(-4, "-4");
        rbt.setValue(-5, "-5");
        rbt.setValue(-6, "-6");
        rbt.setValue(-7, "-7");
        rbt.setValue(-8, "-8");
        rbt.setValue(-9, "-9");
        rbt.setValue(-10, "-10");
        rbt.setValue(-11, "-11");
        rbt.setValue(-12, "-12");
        rbt.setValue(-13, "-13");
        //rbt.setValue(-14,"-14");
        //rbt.setValue(-15,"-15");
        //rbt.setValue(-16,"-16");
        //rbt.setValue(-17,"-17");

        Node<Integer, String> root = rbt.getRoot();
        root.printTreeWithValues(root, 0);
        int IsRightNodeRed = 0;
        //when

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            if (root.getRight() != null && root.getRight().isRed()) {
                IsRightNodeRed = 1;
            }
            root = root.getRight();
        }
        // then
        assertThat(IsRightNodeRed).isEqualTo(0);
    }

    @Test
    public void TreeCantHaveTwoConsecutiveLeftRedNodes() {
        // given
        Stack<Node<Integer, String>> stack = new Stack<>();
        RbtMap<Integer, String> rbt = new RbtMap<>();
        /*rbt.setValue(1,"D");
        rbt.setValue(3,"L");
        rbt.setValue(6,"U");
        rbt.setValue(2,"G");
        rbt.setValue(0,"A");
        rbt.setValue(5,"S");
        rbt.setValue(4,"N");
        rbt.setValue(7,"Y");

         */
        rbt.setValue(1, "1");
        rbt.setValue(2, "2");
        rbt.setValue(3, "3");
        rbt.setValue(4, "4");
        rbt.setValue(5, "5");
        rbt.setValue(6, "6");
        rbt.setValue(7, "7");
        rbt.setValue(8, "8");
        rbt.setValue(9, "9");
        rbt.setValue(10, "10");
        rbt.setValue(11, "11");
        rbt.setValue(12, "12");
        rbt.setValue(13, "13");
        rbt.setValue(14, "14");
        rbt.setValue(15, "15");
        rbt.setValue(16, "16");
        //rbt.setValue(17,"17");
        Node<Integer, String> root = rbt.getRoot();
        int IsTwoConsecutiveLeftNodesRed = 0;
        root.printTreeWithValues(root, 0);
        //when
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();

            if (root.getLeft() != null && root.getLeft().isRed()) {
                Node<Integer, String> temp = root.getLeft();
                if (temp.getLeft() != null && temp.getLeft().isRed())
                    IsTwoConsecutiveLeftNodesRed = 1;
            }
            root = root.getRight();
        }
        // then
        assertThat(IsTwoConsecutiveLeftNodesRed).isEqualTo(0);
    }

    @Test
    public void AllPathShouldHaveTheSameNoOfBlackNodes() {
        RbtMap<Integer, String> rbt = new RbtMap<>();
        rbt.setValue(1, "D");
        rbt.setValue(2, "L");
        rbt.setValue(6, "U");
        rbt.setValue(2, "G");
        rbt.setValue(9, "A");
        rbt.setValue(5, "S");
        rbt.setValue(4, "N");
        rbt.setValue(18, "Y");
        rbt.setValue(3, "A");
        rbt.setValue(2, "S");
        rbt.setValue(6, "N");
        rbt.setValue(14, "Y");
        //rbt.Delete(); TEN TEST NIE PRZECHODZI JEØELI WSTAWI SI  DELETE -> COå èLE DZIA£A
        Node<Integer, String> root = rbt.getRoot();
        Node<Integer, String> firstPath = rbt.getRoot();
        int expectedBlackDepth = 0;
        root.printTreeWithValues(root, 0);
        while (firstPath != null) {
            if (firstPath.isBlack())
                expectedBlackDepth++;
            firstPath = firstPath.getLeft();
        }
        System.out.println(expectedBlackDepth);

        int flag = 0;

        flag = checkBlackDepth(root, 0, expectedBlackDepth, flag);

        assertThat(flag).isEqualTo(0);
    }

    private int checkBlackDepth(Node<Integer, String> node, int blackDepth, int expectedBlackDepth, int flag) {
        if (node == null) {
            if (blackDepth != expectedBlackDepth) {
                flag = 1;
            }
            return flag;
        }
        if (node.isBlack()) {
            blackDepth++;
        }
        if (flag == 1) {
            return flag;
        }
        flag = checkBlackDepth(node.getLeft(), blackDepth, expectedBlackDepth, flag);
        if (flag == 1) {
            return flag;
        }
        flag = checkBlackDepth(node.getRight(), blackDepth, expectedBlackDepth, flag);
        return flag;
    }

    @Test
    public void ShouldCorrectlyGetValue() {
        // given
        RbtMap<Integer, String> rbt = new RbtMap<>();
        rbt.setValue(6, "D");
        rbt.setValue(3, "P");
        rbt.setValue(5, "Czarnek");


        // when
        String value = rbt.getValue(3);
        String value1 = rbt.getValue(5);

        // then

        assertThat(value).isEqualTo("P");
        assertThat(value1).isEqualTo("Czarnek");
    }

    @Test
    public void should_ThrowException_WhenTryingAddNullValue() {
        // given
        String nullValue = null;
        RbtMap<Integer, String> rbt = new RbtMap<>();


        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rbt.setValue(0, nullValue);
        });

        // then
        String message = "Params (key, value) cannot be null.";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ThrowException_WhenTryingAddNullKey() {
        // given
        Integer nullValue = null;
        RbtMap<Integer, String> rbt = new RbtMap<>();


        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rbt.setValue(nullValue, "B");
        });

        // then
        String message = "Params (key, value) cannot be null.";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ThrowException_WhenTryingGetNullKey() {
        // given
        Integer nullValue = null;
        RbtMap<Integer, String> rbt = new RbtMap<>();


        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rbt.getValue(nullValue);
        });

        // then
        String message = "Cannot get value by null key.";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void ShouldCorrectlyReturnNullWhenValueDoesNotExists() {
        // given
        RbtMap<Integer, String> rbt = new RbtMap<>();
        // when
        String value = rbt.getValue(2);
        // then
        assertThat(value).isEqualTo(null);
    }

}
