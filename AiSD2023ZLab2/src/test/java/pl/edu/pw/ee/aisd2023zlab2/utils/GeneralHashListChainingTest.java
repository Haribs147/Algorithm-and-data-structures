package pl.edu.pw.ee.aisd2023zlab2.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.edu.pw.ee.aisd2023zlab2.HashListChaining;
import pl.edu.pw.ee.aisd2023zlab2.HashListChainingModularHashing;
import pl.edu.pw.ee.aisd2023zlab2.services.HashTable;
import static pl.edu.pw.ee.aisd2023zlab2.utils.AdvancedConstructors.createHashInstance;
import static pl.edu.pw.ee.aisd2023zlab2.utils.AdvancedGetters.getHashElemById;
import static pl.edu.pw.ee.aisd2023zlab2.utils.AdvancedGetters.getNumOfElems;

public abstract class GeneralHashListChainingTest {

    private final Class<? extends HashListChaining> hashListClass;
    private HashTable<String> hashString;

    public GeneralHashListChainingTest(Class<? extends HashListChaining> hashListClass) {
        this.hashListClass = hashListClass;
    }

    @Before
    public void setup() {
        hashString = createHashInstance(hashListClass);
    }

    @Test
    public void should_ThrowException_WhenTryingAddNullValue() {
        // given
        String nullValue = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            hashString.add(nullValue);
        });

        // then
        String message = "Value of elem in hash table cannot be null!";

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
            hashString.add(nullValue);
        });

        // then
        String message = "Value of elem in hash table cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ThrowException_WhenTryingToCreateHashWithSizeEqualZero() {
        // given
        int size = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            createHashInstance(size, hashListClass);

        });

        // then
        String message = "Hash size cannot be less than \"1\"!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ReturnCorrectSize_AfterAddingElement() {
        // given
        String value = "Ala";

        // when
        int nOfElemsBeforeAdd = getNumOfElems(hashString);
        hashString.add(value);
        int nOfElemsAfterAdd = getNumOfElems(hashString);

        // then
        assertThat(nOfElemsBeforeAdd).isEqualTo(0);
        assertThat(nOfElemsAfterAdd).isEqualTo(1);
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
    public void should_ReturnCorrectValue_WhenHeapIsNotEmpty() {
        // given
        hashString.add("Ala");
        hashString.add("Ola");
        hashString.add("Ewa");

        // when
        String returnedName = hashString.get("Ola");

        // then
        assertThat(returnedName).isEqualTo("Ola");
    }

    @Test
    public void should_CorrectlyAddThreeDifferentElems_WhenHashSizeEqualOne() {
        // given
        int hashSize = 1;
        HashTable<String> names = new HashListChainingModularHashing<>(hashSize);
        names.add("Ola");
        names.add("Ala");
        names.add("Ula");

        // when
        int nOfElemsInHash = getNumOfElems(names);
        String firstName = getHashElemById(names, 0);
        String secondName = getHashElemById(names, 1);
        String thirdName = getHashElemById(names, 2);

        // then
        assertThat(nOfElemsInHash).isEqualTo(3);
        assertThat(firstName).isEqualTo("Ula");
        assertThat(secondName).isEqualTo("Ala");
        assertThat(thirdName).isEqualTo("Ola");
    }

}
