package Lab_6;

import java.util.*;

public class Q1 {
    public static void main(String[] args) {
        RegistrationPortal portal = new RegistrationPortal();
        portal.add(new Course("CS101", 2, List.of()));
        portal.add(new Course("MATH101", 2, List.of()));
        portal.add(new Course("CS201", 2, List.of("MATH101")));
        portal.register("CS201", "S01");
        portal.register("CS101", "S01");
        portal.register("CS101", "S02");
        portal.register("CS101", "S03");
        portal.register("CS101", "S01");
        portal.completePrerequisite("MATH101", "S01");
        portal.register("CS201", "S01");
        portal.drop("CS101", "S01");
        portal.promoteWaitlist("CS101");
        portal.print("CS101");
    }

    static class Course {
        final String code;
        final int capacity;
        final Set<String> enrolled = new HashSet<>();
        final List<String> prerequisites;
        final LinkedList<String> waitlist = new LinkedList<>();
        Course(String code, int capacity, List<String> prerequisites) {
            this.code = code; this.capacity = capacity; this.prerequisites = new ArrayList<>(prerequisites);
        }
    }
    static class RegistrationPortal {
        private final HashMap<String, Course> courses = new HashMap<>();
        private final Set<String> completed = new HashSet<>();
        void add(Course course) { courses.put(course.code, course); }
        void completePrerequisite(String course, String student) { completed.add(course + ":" + student); }
        void register(String code, String student) {
            Course course = courses.get(code);
            if (course == null) { System.out.println("Course not found."); return; }
            if (course.enrolled.contains(student) || course.waitlist.contains(student)) return;
            boolean ready = course.prerequisites.stream().allMatch(p -> completed.contains(p + ":" + student));
            if (!ready) { System.out.println(student + " lacks prerequisites for " + code); return; }
            if (course.enrolled.size() < course.capacity) course.enrolled.add(student);
            else { course.waitlist.add(student); System.out.println(student + " added to waitlist."); }
        }
        void promoteWaitlist(String code) {
            Course course = courses.get(code);
            if (course == null) {
                System.out.println("Course not found.");
                return;
            }
            while (course.enrolled.size() < course.capacity && !course.waitlist.isEmpty())
                course.enrolled.add(course.waitlist.removeFirst());
        }
        void drop(String code, String student) {
            Course course = courses.get(code);
            if (course != null) course.enrolled.remove(student);
        }
        void print(String code) {
            Course c = courses.get(code);
            if (c == null) {
                System.out.println("Course not found.");
                return;
            }
            System.out.println(code + " enrolled=" + c.enrolled + ", waitlist=" + c.waitlist);
        }
    }
}
