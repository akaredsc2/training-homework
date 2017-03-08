package org.vitaly.homework02.inner;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vitaly on 17.02.17.
 */
public class MarkBook {
    private EnumMap<Semester, List<MarkBookEntry>> marks;
    private String studentName;

    public MarkBook(String studentName) {
        this.studentName = studentName;
        this.marks = new EnumMap<>(Semester.class);
        for (Semester s : Semester.values()) {
            marks.put(s, new LinkedList<>());
        }
    }

    public String getStudentName() {
        return studentName;
    }

    public List<MarkBookEntry> getMarks(Semester semester) {
        return marks.get(semester);
    }

    public void addMarkBookEntry(Semester semester, MarkBookEntry entry) {
        List<MarkBookEntry> entries = this.marks.get(semester);

        if (!entries.contains(entry)) {
            entries.add(entry);
        }
    }

    public enum Mark {
        A, B, C, D, E, F
    }

    public enum Semester {
        I, II, III, IV, V, VI, VII, VIII
    }

    public enum SubjectType {
        EXAM, REGULAR, DIFF_REGULAR
    }

    public MarkBookEntry createMarkBookEntry(String subjectName, SubjectType type, String teacherName, Mark mark) {
        if (subjectName == null) {
            throw new IllegalArgumentException("No subject supplied!");
        }
        if (type == null) {
            throw new IllegalArgumentException("No subject type supplied!");
        }
        if (teacherName == null) {
            throw new IllegalArgumentException("No teacher name supplied!");
        }
        if (mark == null) {
            throw new IllegalArgumentException("No mark supplied!");
        }
        return new MarkBookEntry(subjectName, type, teacherName, mark, LocalDate.now());
    }

    public class MarkBookEntry {
        private String subjectName;
        private SubjectType subjectType;
        private String teacherName;
        private Mark mark;
        private LocalDate date;

        public MarkBookEntry(String subjectName, SubjectType type, String teacherName, Mark mark, LocalDate date) {
            this.subjectName = subjectName;
            this.subjectType = type;
            this.teacherName = teacherName;
            this.mark = mark;
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            MarkBookEntry that = (MarkBookEntry) o;

            return subjectName.equals(that.subjectName);
        }

        @Override
        public int hashCode() {
            return subjectName.hashCode();
        }

        @Override
        public String toString() {
            return "MarkBookEntry{" +
                    "subjectName='" + subjectName + '\'' +
                    ", subjectType=" + subjectType +
                    ", teacherName='" + teacherName + '\'' +
                    ", mark=" + mark +
                    ", date=" + date +
                    '}';
        }
    }
}
