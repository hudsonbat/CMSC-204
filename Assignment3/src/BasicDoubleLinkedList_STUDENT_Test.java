import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicDoubleLinkedList_STUDENT_Test {
	
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	BasicDoubleLinkedList<Car> linkedCar;
	StringComparator comparator;
	DoubleComparator comparatorD;
	CarComparator comparatorCar;
	
	public Car a=new Car("Tesla", "Model Y", 2005);
	public Car b=new Car("BMW", "M3", 2005);
	public Car c=new Car("Porsche", "Cayenne", 2005);
	public Car d=new Car("Mini Cooper", "Countryman", 2005);
	public Car e=new Car("Dodge", "Challenger", 2005);
	public Car f=new Car("Cadillac", "Escalade", 2005);

	@BeforeEach
	void setUp() throws Exception {
		
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hi");
		linkedString.addToEnd("There");
		comparator = new StringComparator();
		
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(15.5);
		linkedDouble.addToEnd(100.8);
		comparatorD = new DoubleComparator();
		
		linkedCar= new BasicDoubleLinkedList<Car>();
		linkedCar.addToEnd(a);
		linkedCar.addToEnd(b);
		comparatorCar = new CarComparator();
	}
		

	@AfterEach
	void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		linkedCar = null;
		comparatorD = null;
		comparator = null;
	}

	@Test
	void testAddToEnd() {
		assertEquals("There", linkedString.getLast());
		linkedString.addToEnd("Everyone");
		assertEquals("Everyone", linkedString.getLast());
		
		assertEquals(b,linkedCar.getLast());
		linkedCar.addToEnd(d);
		assertEquals(d,linkedCar.getLast());
	}

	@Test
	void testAddToFront() {
		assertEquals("Hi", linkedString.getFirst());
		linkedString.addToFront("Start");
		assertEquals("Start", linkedString.getFirst());
		
		assertEquals(a,linkedCar.getFirst());
		linkedCar.addToFront(e);
		assertEquals(e,linkedCar.getFirst());
	}

	@Test
	void testRemove() {
		assertEquals(a, linkedCar.getFirst());
		assertEquals(b, linkedCar.getLast());
		linkedCar.addToFront(e);
		assertEquals(e, linkedCar.getFirst());
		linkedCar.remove(e, comparatorCar);
		assertEquals(a, linkedCar.getFirst());
		linkedCar.addToEnd(d);
		assertEquals(d, linkedCar.getLast());
		linkedCar.remove(d, comparatorCar);
		assertEquals(b, linkedCar.getLast());
		linkedCar.addToFront(f);
		assertEquals(f, linkedCar.getFirst());
		assertEquals(b, linkedCar.getLast());
		linkedCar.remove(b, comparatorCar);
		assertEquals(f, linkedCar.getFirst());
		assertEquals(a, linkedCar.getLast());
	}

	@Test
	void testRetrieveFirstElement() {
		assertEquals(a, linkedCar.getFirst());
		linkedCar.addToFront(c);
		assertEquals(c, linkedCar.getFirst());
		assertEquals(c, linkedCar.retrieveFirstElement());
		assertEquals(a,linkedCar.getFirst());
		assertEquals(a, linkedCar.retrieveFirstElement());
		assertEquals(b,linkedCar.getFirst());
		
		assertEquals("Hi", linkedString.getFirst());
		linkedString.addToFront("I");
		assertEquals("I", linkedString.getFirst());
		assertEquals("I", linkedString.retrieveFirstElement());
		assertEquals("Hi",linkedString.getFirst());
		assertEquals("Hi", linkedString.retrieveFirstElement());
		assertEquals("There",linkedString.getFirst());
	}

	@Test
	void testRetrieveLastElement() {
		assertEquals(b, linkedCar.getLast());
		linkedCar.addToEnd(f);
		assertEquals(f, linkedCar.getLast());
		assertEquals(f, linkedCar.retrieveLastElement());
		assertEquals(b,linkedCar.getLast());
		
		assertEquals("There", linkedString.getLast());
		linkedString.addToEnd("Jim");
		assertEquals("Jim", linkedString.getLast());
		assertEquals("Jim", linkedString.retrieveLastElement());
		assertEquals("There",linkedString.getLast());
	}

	@Test
	void testToArrayList() {
		ArrayList<Car> list;
		linkedCar.addToFront(f);
		linkedCar.addToEnd(e);
		list = linkedCar.toArrayList();
		assertEquals(f,list.get(0));
		assertEquals(a,list.get(1));
		assertEquals(b,list.get(2));
		assertEquals(e,list.get(3));
	}

	@Test
	void testGetFirst() {
		assertEquals("Hi", linkedString.getFirst());
		linkedString.addToFront("Oh");
		assertEquals("Oh", linkedString.getFirst());
		
		assertEquals(a,linkedCar.getFirst());
		linkedCar.addToFront(f);
		assertEquals(f,linkedCar.getFirst());
	}

	@Test
	void testGetLast() {
		assertEquals("There", linkedString.getLast());
		linkedString.addToEnd("Peter");
		assertEquals("Peter", linkedString.getLast());
		
		assertEquals(b,linkedCar.getLast());
		linkedCar.addToEnd(d);
		assertEquals(d,linkedCar.getLast());
	}

	@Test
	void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedDouble.getSize());
		assertEquals(2,linkedCar.getSize());
	}

	@Test
	void testIterator() {
		linkedCar.addToFront(c);
		linkedCar.addToEnd(e);
		ListIterator<Car> iteratorCar = linkedCar.iterator();
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(c, iteratorCar.next());
		assertEquals(a, iteratorCar.next());
		assertEquals(b, iteratorCar.next());
		assertEquals(e, iteratorCar.next());
		assertEquals(true, iteratorCar.hasPrevious());
		assertEquals(e, iteratorCar.previous());
		assertEquals(b, iteratorCar.previous());
		assertEquals(a, iteratorCar.previous());
		assertEquals(c, iteratorCar.previous());
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
			// Puts cars in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
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


