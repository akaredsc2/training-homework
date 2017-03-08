package org.vitaly.homework02.inner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 17.02.17.
 */

public class MarkBookTest {
    @Test
    public void testCreateMarkBookEntry() throws Exception {
        MarkBook markBook = new MarkBook("Vitaly Sharapov");
        MarkBook.MarkBookEntry entry =
                markBook.createMarkBookEntry("math", MarkBook.SubjectType.EXAM, "someone", MarkBook.Mark.C);
        markBook.addMarkBookEntry(MarkBook.Semester.I, entry);
        assertEquals(1, markBook.getMarks(MarkBook.Semester.I).size());

        MarkBook markBook1 = new MarkBook("Karsa Orlong");
        MarkBook.MarkBookEntry entry1
                = markBook1.createMarkBookEntry("ethics", MarkBook.SubjectType.REGULAR, "Samar Dev", MarkBook.Mark.F);
        markBook1.addMarkBookEntry(MarkBook.Semester.VI, entry1);
        assertEquals(0, markBook1.getMarks(MarkBook.Semester.I).size());
        assertEquals(1, markBook1.getMarks(MarkBook.Semester.VI).size());

        MarkBook.MarkBookEntry entry2
                = markBook1.createMarkBookEntry("ethics", MarkBook.SubjectType.DIFF_REGULAR, "Rulad Sengar", MarkBook.Mark.A);
        markBook1.addMarkBookEntry(MarkBook.Semester.VI, entry2);
        assertEquals(1, markBook1.getMarks(MarkBook.Semester.VI).size());
    }
}