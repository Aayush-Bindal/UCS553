package Lab_6;

import java.util.*;

public class Q2 {
    public static void main(String[] args) {
        LinkedList<String> playlist = new LinkedList<>(List.of("Intro", "Song A", "Song B"));
        playlist.addFirst("Priority Song");
        playlist.add(2, "Inserted Song");
        playlist.addLast("Outro");
        playlist.remove("Song A");
        playlist.add("Song B");
        System.out.println("Forward:");
        ListIterator<String> iterator = playlist.listIterator();
        while (iterator.hasNext()) System.out.println(iterator.next());
        System.out.println("Backward:");
        while (iterator.hasPrevious()) System.out.println(iterator.previous());
        removeDuplicates(playlist);
        System.out.println("After duplicate removal: " + playlist);
    }

    private static void removeDuplicates(LinkedList<String> playlist) {
        Set<String> seen = new HashSet<>();
        ListIterator<String> iterator = playlist.listIterator();
        while (iterator.hasNext()) if (!seen.add(iterator.next())) iterator.remove();
    }
}
