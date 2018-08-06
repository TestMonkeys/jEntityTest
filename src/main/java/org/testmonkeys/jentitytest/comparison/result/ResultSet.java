package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;


@SuppressWarnings({"ReturnOfThis", "ClassWithTooManyMethods", "ClassWithTooManyDependents"})
public class ResultSet implements List<ComparisonResult> {

    private final ArrayList<ComparisonResult> arrayList = new ArrayList<>();

    public ResultSet with(Status status, ComparisonContext context, Object actual, Object
            expected){
        arrayList.add(new ComparisonResult(status, context, actual, expected));
        return this;
    }

    public ResultSet with(ComparisonResult result) {
        arrayList.add(result);
        return this;
    }


    public boolean add(ComparisonResult e) {
        return arrayList.add(e);
    }

    @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter")
    public boolean containsAll(Collection<?> collection) {
        return arrayList.containsAll(collection);
    }

    @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter")
    @Override
    public boolean addAll(Collection<? extends ComparisonResult> collection) {
        return arrayList.addAll(collection);
    }

    @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter")
    @Override
    public boolean addAll(int index, Collection<? extends ComparisonResult> collection) {
        return arrayList.addAll(index,collection);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return arrayList.equals(obj);
    }

    public int hashCode() {
        return arrayList.hashCode();
    }

    public int size() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    public boolean contains(Object o) {
        return arrayList.contains(o);
    }

    public int indexOf(Object o) {
        return arrayList.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return arrayList.lastIndexOf(o);
    }

    public Object[] toArray() {
        return arrayList.toArray();
    }


    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray(Object[] a) {
        return arrayList.toArray();
    }

    public ComparisonResult get(int index) {
        return arrayList.get(index);
    }

    @Override
    public ComparisonResult set(int index, ComparisonResult element) {
        return arrayList.set(index,element);
    }

    @Override
    public void add(int index, ComparisonResult element) {
        arrayList.add(index,element);
    }

    public ComparisonResult remove(int index) {
        return arrayList.remove(index);
    }

    public boolean remove(Object o) {
        return arrayList.remove(o);
    }

    public void clear() {
        arrayList.clear();
    }

    @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter")
    public boolean removeAll(Collection<?> collection) {
        return arrayList.removeAll(collection);
    }

    @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter")
    public boolean retainAll(Collection<?> collection) {
        return arrayList.retainAll(collection);
    }

    public ListIterator<ComparisonResult> listIterator(int index) {
        return arrayList.listIterator(index);
    }

    public ListIterator<ComparisonResult> listIterator() {
        return arrayList.listIterator();
    }

    public Iterator<ComparisonResult> iterator() {
        return arrayList.iterator();
    }

    public List<ComparisonResult> subList(int fromIndex, int toIndex) {
        return arrayList.subList(fromIndex, toIndex);
    }

    public Spliterator<ComparisonResult> spliterator() {
        return arrayList.spliterator();
    }
}
