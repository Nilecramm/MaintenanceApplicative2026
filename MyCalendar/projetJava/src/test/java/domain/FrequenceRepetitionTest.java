package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrequenceRepetitionTest {

    @Test
    void creeFrequenceValide() {
        FrequenceRepetition freq = new FrequenceRepetition(7);
        assertEquals(7, freq.value());
    }

    @Test
    void frequenceZeroLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new FrequenceRepetition(0));
    }

    @Test
    void frequenceNegativeLeveException() {
        assertThrows(IllegalArgumentException.class, () -> new FrequenceRepetition(-1));
    }

    @Test
    void deuxFrequencesIdentiquesSontEgales() {
        FrequenceRepetition f1 = new FrequenceRepetition(14);
        FrequenceRepetition f2 = new FrequenceRepetition(14);
        assertEquals(f1, f2);
    }
}
