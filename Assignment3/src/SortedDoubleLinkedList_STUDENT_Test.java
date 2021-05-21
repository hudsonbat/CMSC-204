import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.ListIterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SortedDoubleLinkedList_STUDENT_Test {
	
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	SortedDoubleLinkedList<Car> sortedLinkedCar;
	StringComparator comparator;
	DoubleComparator comparatorD;
	CarComparator comparatorCar;
	
	public Car a=new Car("Tesla", "Model Y", 2005);
	public Car b=new Car("Mazda", "Miata", 2005);
	public Car c=new Car("Porsche", "Cayenne", 2005);
	public Car d=new Car("Mini Cooper", "Countryman", 2005);
	public Car e=new Car("Dodge", "Challenger", 2005);
	public Car f=new Car("Cadillac", "Escalade", 2005);

	@BeforeEach
	void setUp() throws Exception {
		
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
		
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
		
		comparatorCar = new CarComparator();
		sortedLinkedCar = new SortedDoubleLinkedList<Car>(comparatorCar);
	}

	@AfterEach
	void tearDown() throws Exception {
		comparator = null;
		comparatorD = null;
		comparatorCar = null;
		sortedLinkedString = null;
		sortedLinkedDouble = null;
		sortedLinkedCar = null;
	}

	@Test
	void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Not Allowed");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}


	@Test
	void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Also Not Allowed");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	void testIteratorDouble() {
		sortedLinkedDouble.add(new Double(11.8));
		sortedLinkedDouble.add(new Double(10.5));
		sortedLinkedDouble.add(new Double(18.4));
		sortedLinkedDouble.add(new Double(2.9));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(new Double(2.9), iterator.next());
		assertEquals(new Double(10.5), iterator.next());
		assertEquals(new Double(11.8), iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(new Double(11.8), iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}
	
	
	@Test
	void testIteratorString() {
		sortedLinkedString.add("Zinc");
		sortedLinkedString.add("Sodium");
		sortedLinkedString.add("Calcium");
		sortedLinkedString.add("Iron");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Calcium", iterator.next());
		assertEquals("Iron", iterator.next());
		assertEquals("Sodium", iterator.next());
		assertEquals("Zinc", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Zinc", iterator.previous());
		assertEquals("Sodium", iterator.previous());
		assertEquals("Iron", iterator.previous());
	}
	
	
	@Test
	void testIteratorCar() {
		sortedLinkedDouble.add(new Double(11.8));
		sortedLinkedDouble.add(new Double(10.5));
		sortedLinkedDouble.add(new Double(18.4));
		sortedLinkedDouble.add(new Double(2.9));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(new Double(2.9), iterator.next());
		assertEquals(new Double(10.5), iterator.next());
		assertEquals(new Double(11.8), iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(new Double(11.8), iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}

	@Test
	void testAddCar() {
		sortedLinkedCar.add(e);
		sortedLinkedCar.add(d);
		sortedLinkedCar.add(b);
		assertEquals(e, sortedLinkedCar.getFirst());
		assertEquals(b, sortedLinkedCar.getLast());
		sortedLinkedCar.add(f);
		sortedLinkedCar.add(a);
		assertEquals(e, sortedLinkedCar.getFirst());
		assertEquals(a, sortedLinkedCar.getLast());
		assertEquals(a,sortedLinkedCar.retrieveLastElement());
		assertEquals(b, sortedLinkedCar.getLast());
	}
	
	
	@Test
	void testAddDouble() {
		sortedLinkedDouble.add(new Double(129.783));
		sortedLinkedDouble.add(new Double(129.124));
		sortedLinkedDouble.add(new Double(137.765));
		assertEquals(new Double(129.124), sortedLinkedDouble.getFirst());
		assertEquals(new Double(137.765), sortedLinkedDouble.getLast());
		sortedLinkedDouble.add(new Double(137.654));
		sortedLinkedDouble.add(new Double(137.998));
		assertEquals(new Double(129.124), sortedLinkedDouble.getFirst());
		assertEquals(new Double(137.998), sortedLinkedDouble.getLast());
		assertEquals(new Double(137.998),sortedLinkedDouble.retrieveLastElement());
		assertEquals(new Double(137.765), sortedLinkedDouble.getLast());
	}
	

	@Test
	void testRemoveCar() {
		sortedLinkedCar.add(a);
		sortedLinkedCar.add(c);
		assertEquals(c, sortedLinkedCar.getFirst());
		assertEquals(a, sortedLinkedCar.getLast());
		sortedLinkedCar.add(e);
		assertEquals(c, sortedLinkedCar.getFirst());
		assertEquals(a, sortedLinkedCar.getLast());
		assertEquals(3,sortedLinkedCar.getSize());
		sortedLinkedCar.remove(a, comparatorCar);
		assertEquals(c, sortedLinkedCar.getFirst());
		assertEquals(e, sortedLinkedCar.getLast());
		assertEquals(2,sortedLinkedCar.getSize());
	}
	
	
	@Test
	void testRemoveDouble() {
		sortedLinkedDouble.add(new Double(124.123));
		sortedLinkedDouble.add(new Double(124.124));
		sortedLinkedDouble.add(new Double(124.126));
		assertEquals(new Double(124.123), sortedLinkedDouble.getFirst());
		assertEquals(new Double(124.126), sortedLinkedDouble.getLast());
		sortedLinkedDouble.add(new Double(124.125));
		sortedLinkedDouble.remove(new Double(124.123), comparatorD);
		assertEquals(new Double(124.124), sortedLinkedDouble.getFirst());
		sortedLinkedDouble.remove(new Double(124.126), comparatorD);
		assertEquals(new Double(124.125), sortedLinkedDouble.getLast());
	}
	
	
	
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class CarComparator implements Comparator<Car>
	{

		@Override
		public int compare(Car arg0, Car arg1) {
			return arg0.getModel().compareTo(arg1.getModel());
		}		
	}
	
	private class Car{
		String make;
		String model;
		int year;
		
		public Car(String make, String model, int year){
			this.make = make;
			this.model = model;
			this.year = year;
		}
		
		public String getMake(){
			return make;
		}
		public String getModel(){
			return model;
		}
		public int getYear(){
			return year;
		}
		
		public String toString() {
			return (getMake()+" "+getModel()+" "+getYear());
		}
	}

}
