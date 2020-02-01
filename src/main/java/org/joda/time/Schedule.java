package org.joda.time;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Schedule implements Serializable, List<Interval> {

    private List<Interval> intervals = new LinkedList<>();


    public static Schedule empty() {
        return new Schedule();
    }


    public Schedule(List<Interval> intervals) {
        this.intervals = new LinkedList<>(intervals);
    }

    public Schedule() {

    }
    // Schedule methods

    public Schedule overlap(Schedule otherSchedule) {
        return intersect(otherSchedule);
    }

    public Schedule intersect(Schedule otherSchedule) {
        if (otherSchedule.isEmpty()) {
            return empty();
        }
        final List<Interval> otherIntervals = otherSchedule.intervals;

        final LinkedList<Interval> intersectedIntervals = new LinkedList<>();

        for (Interval interval : this.intervals) {
            for (Interval otherInterval : otherIntervals) {
                if (interval.overlaps(otherInterval)) {
                    intersectedIntervals.add(interval.overlap(otherInterval));
                }
            }
        }
        return new Schedule(intersectedIntervals);
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Interval)) {
            return false;
        }
        return intervals.stream().anyMatch(it -> it.contains((ReadableInterval) o));
    }


    // Delegated List methods

    @Override
    public int size() {
        return intervals.size();
    }

    @Override
    public boolean isEmpty() {
        return intervals.isEmpty();
    }


    @Override
    public int indexOf(Object o) {
        return intervals.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return intervals.lastIndexOf(o);
    }

    @Override
    public Object[] toArray() {
        return intervals.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return intervals.toArray(a);
    }

    @Override
    public Interval get(int index) {
        return intervals.get(index);
    }

    @Override
    public Interval set(int index, Interval element) {
        return intervals.set(index, element);
    }

    @Override
    public boolean add(Interval interval) {
        return intervals.add(interval);
    }

    @Override
    public void add(int index, Interval element) {
        intervals.add(index, element);
    }

    @Override
    public Interval remove(int index) {
        return intervals.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return intervals.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return intervals.containsAll(c);
    }

    @Override
    public void clear() {
        intervals.clear();
    }

    @Override
    public boolean addAll(Collection<? extends Interval> c) {
        return intervals.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Interval> c) {
        return intervals.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return intervals.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return intervals.retainAll(c);
    }

    @Override
    public ListIterator<Interval> listIterator(int index) {
        return intervals.listIterator(index);
    }

    @Override
    public ListIterator<Interval> listIterator() {
        return intervals.listIterator();
    }

    @Override
    public Iterator<Interval> iterator() {
        return intervals.iterator();
    }

    @Override
    public List<Interval> subList(int fromIndex, int toIndex) {
        return intervals.subList(fromIndex, toIndex);
    }

    @Override
    public void forEach(Consumer<? super Interval> action) {
        intervals.forEach(action);
    }

    @Override
    public Spliterator<Interval> spliterator() {
        return intervals.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super Interval> filter) {
        return intervals.removeIf(filter);
    }

    @Override
    public void replaceAll(UnaryOperator<Interval> operator) {
        intervals.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super Interval> c) {
        intervals.sort(c);
    }


}
