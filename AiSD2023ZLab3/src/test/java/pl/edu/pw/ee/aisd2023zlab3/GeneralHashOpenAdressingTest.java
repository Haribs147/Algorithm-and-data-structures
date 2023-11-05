package pl.edu.pw.ee.aisd2023zlab3;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedConstructors.createHashInstance;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;

public abstract class GeneralHashOpenAdressingTest {

    private final Class<? extends HashOpenAdressing> hashListClass;
    private HashTable<String> hashString;

    public GeneralHashOpenAdressingTest(Class<? extends HashOpenAdressing> hashListClass) {
        this.hashListClass = hashListClass;
    }

    @Before
    public void setup() {
        hashString = createHashInstance(hashListClass);
    }

    @Test
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int size = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            createHashInstance(size, hashListClass);

        });

        // then
        String message = "Initial size of hash table cannot be lower than 1!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }


    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {

        // given

        String newEleme = "P. Czarnek";

        // when
        int nOfElemsBeforePut = getNumOfElems(hashString);
        hashString.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(hashString);

        // then
        assertThat(nOfElemsBeforePut).isEqualTo(0);
        assertThat(nOfElemsAfterPut).isEqualTo(1);
    }

    @Test
    public void should_CorrectlyReturnNullWhenElemDoesNotExistInTable() {
        // given
        String newEleme1 = "P. Czarnek";
        String newEleme2 = "JP2";
        String newEleme3 = "JP3";

        // when
        hashString.put(newEleme1);
        hashString.put(newEleme2);
        hashString.put(newEleme3);
        String Elem0 = hashString.get("nie_wiem_co");
        String Elem2 = hashString.get(newEleme2);
        String Elem1 = hashString.get(newEleme1);
        String Elem3 = hashString.get(newEleme3);
        // then
        assertThat(Elem0).isEqualTo(null);
        assertThat(newEleme1).isEqualTo(Elem1);
        assertThat(newEleme2).isEqualTo(Elem2);
        assertThat(newEleme3).isEqualTo(Elem3);
    }

    @Test
    public void should_CorrectlyGetElemsWhenSomethingWasPreviouslyDeleted() {
        // given
        String newEleme1 = "JP2";
        String newEleme2 = "JP2";
        String newEleme3 = "JP2";

        // when
        hashString.put(newEleme1);
        hashString.put(newEleme2);
        hashString.put(newEleme3);
        hashString.delete(newEleme1);
        String Elem2 = hashString.get(newEleme2);
        hashString.delete(newEleme2);
        String Elem3 = hashString.get(newEleme3);
        hashString.delete(newEleme3);
        String Elem4 = hashString.get("JP2");
        // then
        assertThat(newEleme2).isEqualTo(Elem2);
        assertThat(newEleme3).isEqualTo(Elem3);
        assertThat(Elem4).isEqualTo(null);
    }


    @Test
    public void should_CorrectlygetElems() {
        // given
        String newEleme1 = "P. Czarnek";
        String newEleme2 = "JP2";
        String newEleme3 = "JP2";

        // when
        hashString.put(newEleme1);
        hashString.put(newEleme2);
        hashString.put(newEleme3);
        String Elem2 = hashString.get(newEleme2);
        String Elem1 = hashString.get(newEleme1);
        String Elem3 = hashString.get(newEleme3);
        // then
        assertThat(newEleme1).isEqualTo(Elem1);
        assertThat(newEleme2).isEqualTo(Elem2);
        assertThat(newEleme3).isEqualTo(Elem3);
    }

    @Test
    public void should_CorrectlyDeleteElems() {
        // given
        String newElem = "P. Czarnek";

        // when
        hashString.put(newElem);
        int nBeforeDelete = getNumOfElems(hashString);
        hashString.delete("P. Czarnek");
        int nAfterDelete = getNumOfElems(hashString);
        String deletedelem = hashString.get("P. Czarnek");

        // then
        assertThat(nBeforeDelete).isEqualTo(1);
        assertThat(nAfterDelete).isEqualTo(0);
        assertThat(deletedelem).isEqualTo(null);
    }

    @Test
    public void should_ThrowException_WhenTryingAddNullValue() {
        // given
        String nullValue = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            hashString.put(nullValue);
        });

        // then
        String message = "Input elem cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ThrowException_WhenTryingGetNullValue() {
        // given
        String nullValue = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            hashString.get(nullValue);
        });

        // then
        String message = "Input elem cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }


    @Test
    public void should_ThrowException_WhenTryingDeleteNullValue() {
        // given
        String nullValue = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            hashString.delete(nullValue);
        });

        // then
        String message = "Input elem cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100, 1000})
    void should_NotThrowException_WhenCreatingHashWithSizeBiggerThanZero(int hashSize) {
        // given, when
        createHashInstance(hashSize, hashListClass);

        // then
        assert true;
    }

    @Test
    public void should_NoOfElemsBeTheSame_WhenTryingDeleteValueThatDoesNotExists() {
        // given
        String value = "Ola";
        hashString.put(value);
        int nOfElemsBeforeDelete = getNumOfElems(hashString);

        // when
        hashString.delete("Ala");
        int nOfElemsAfterDelete = getNumOfElems(hashString);
        // then
        assertThat(nOfElemsBeforeDelete).isEqualTo(1);
        assertThat(nOfElemsAfterDelete).isEqualTo(1);
    }

    @Test
    public void should_ReturnNull_WhenTryingGetValueThatDoesNotExists() {
        // given
        hashString.put("Ola");
        String NotExists = "Not Null";

        // when
        NotExists = hashString.get("Ala");
        // then
        assertThat(NotExists).isEqualTo(null);
    }


}