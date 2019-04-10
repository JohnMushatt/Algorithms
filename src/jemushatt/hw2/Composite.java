package jemushatt.hw2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Stack;

/**
 * Homework 2, Question 3: Data Type Exercise
 * 
 * Copy this class into your USERID.hw2 package and complete.
 * 
 * Note: You should not attempt to store as a single BigInteger the value of the
 * composite number. That is, only keep track of the factor/exponents linked
 * list only.
 */
public class Composite {

	/**
	 * Keep track of the linked list of factor/exponents starting with this head.
	 */
	Node head;

	/**
	 * Constructs a Composite with the specified value, which may not be one, zero
	 * or negative.
	 */
	public Composite(long val) {
		this(BigInteger.valueOf(val));
	}

	/**
	 * Constructs a Composite with the specified value, which may not be zero or
	 * negative.
	 * 
	 * Must handle unit case properly.
	 */
	public Composite(BigInteger val) {
		if (val.compareTo(BigInteger.ZERO) <= 0) {
			throw new IllegalArgumentException("Composite must be a non-negative value.");
		} else if (val.compareTo(BigInteger.ONE) == 0) {
			this.head = new Node(1, 1);
		} else {
			this.head = factorize(val).head;
		}
		// FILL in here
	}

	/**
	 * Helper constructor only by use within this class, for creating a new (empty)
	 * composite object. You will find this useful in multiply.
	 * 
	 * Must be private to ensure no one outside this class calls it directly. Note
	 * that the Composite object returned is treated like the value 1.
	 */
	Composite() {
		this.head = new Node(1,1);
	}

	/**
	 * Returns string representation as a sequence of factors with primes
	 * 
	 * 125 returns "5^3" 36 returns "2^2 * 3^2"
	 * 
	 * 1 returns "1" (special case)
	 */
	public String toString() {
		// Temp to hold current head
		Node current = this.head;
		// Output string
		String output = "";
		// While we are not at the end of the composite object
		while (current != null) {
			if (current.next != null) {
				output += current + " * ";
			} else {
				output += current;
			}
			current = current.next;
		}
		return output;
	}

	/**
	 * Determine if two composite values are equal to each other.
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (o instanceof Composite) {
			Composite other = (Composite) o;

			if (this.value().longValue() == other.value().longValue()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Return value of Composite as a single BigInteger.
	 * 
	 * Necessary when adding two composite numbers a+b when gcd(a,b) != a and
	 * gcd(a,b) != b. Note this is typically the case with two random numbers.
	 * 
	 * Also useful for testing.
	 * 
	 * @return a single BigInteger value representing actual value of Composite
	 *         number.
	 */
	public BigInteger value() {
		BigInteger val = new BigInteger("1");
		Node current = this.head;
		while (current != null) {
			long term = current.factor;
			int power = current.power;
			term = (long) Math.pow(term, power);
			val = new BigInteger("" + val.longValue() * term);
			current = current.next;
		}
		return val;
	}

	/**
	 * Determine if Composite represents a prime number.
	 * 
	 * Note: the unit composite number (i.e., 1) is not a prime number.
	 * 
	 * See https://en.wikipedia.org/wiki/Prime_number#Primality_of_one
	 */
	public boolean isPrime() {
		boolean isPrime = false;
		int count =1;
		Node current = this.head;
		while(current!=null) {
			count*=current.power;
			current = current.next;
		}
		if(this.head.factor==1) {
			return false;
		}
		else if(this.head.factor==2  && this.head.power==1&& this.head.next==null) {
			return true;
		}
		else if(count>=2) {
			return false;
		}
		return isPrime;
	}

	/**
	 * Determine if Composite represents the unit number 1.
	 */
	public boolean isUnit() {
		if(this.head.factor==1) {
			return true;
		}
		return false;
	}

	/**
	 * Computes sum of two composite numbers.
	 * 
	 * For full credit, performance of code must be directly proportional to N and M
	 * (where N is number of factors in 'this' and M is number of factors in comp)
	 * PLUS the extra cost of factoring the BigInteger (this + comp)/gcd(this,comp).
	 * 
	 * In other words, for full credit, your code must attempt to do the following:
	 * a) Find a common factor to both and then add together the remaining terms.
	 * 
	 * (2^3 * 3^2 * 5 * 7) + (2^2 * 5^2 * 11) = 2520 + 1100
	 * 
	 * In the above, the common factor is (2^2 * 5) which is extracted, leaving
	 * behind the remainder of this (2 * 3^2 * 7) and the remainder of comp (5 *
	 * 11).
	 * 
	 * (2^2 * 5) * ( 2 * 3^2 * 7 + 5*11) = 20 * ( 126 + 55 )
	 * 
	 * The following logic can be used to reflect the result above. That is, once
	 * you are able to compute the 'common' Composite number, you multiply it by the
	 * Composite value which is the result of adding the two remainders together,
	 * each converted first to a BigInteger.
	 * 
	 * common.multiply(factorize(remainderComp.value().add(remainder.value())));
	 * 
	 * @param comp
	 * @return
	 */
	public Composite add(Composite comp) {
		//return new Composite(this.value().add(comp.value()));
		Composite number = null;
		BigInteger numerator = this.value().add(comp.value());
		BigInteger denomenator = this.gcd(comp).value();
		BigInteger result = numerator.divide(denomenator);
		return number = new Composite(result.multiply(denomenator));
	}

	/**
	 * Computes product of two composite numbers.
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in self and
	 * M is the number of factors in comp.
	 * 
	 * Simply returns 'this' when asked to multiply by 1 (the unit Composite
	 * number). Alternatively, if the unit composite number is asked to be
	 * multiplied by another composite number, then that number is simply returned.
	 * 
	 * @param comp
	 * @return
	 */
	public Composite multiply(Composite comp) {
		if(comp.head.factor==1) {
			return this;
		}
		else if(this.head.factor==1) {
			return comp;
		}
		else {
			Composite num = null;
			Node currentThis = this.head;
			Node currentComp = comp.head;
			BigInteger thisSum = new BigInteger("1");
			BigInteger compSum = new BigInteger("1");
			while(currentThis!=null) {
				Long val = (long) Math.pow(currentThis.factor,currentThis.power);
				thisSum.multiply(new BigInteger("" + val));
				currentThis = currentThis.next;
			}
			while(currentComp!=null) {
				Long val = (long) Math.pow(currentComp.factor,currentComp.power);

				compSum.multiply(new BigInteger("" + val));

				//compSum *= (long) Math.pow(currentComp.factor,currentComp.power);
				currentComp = currentComp.next;

			}
			return new Composite(thisSum.multiply(compSum));
		}
	}

	/**
	 * Computes greatest common divisor with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Greatest_common_divisor
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in self and
	 * M is the number of factors in comp.
	 * 
	 * The greatest common divisor of (comp,1) is 1
	 * 
	 * When there is no common divisor (other than the value 1) this method returns
	 * a unit composite number.
	 * 
	 * @param comp other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite gcd(Composite comp) {
		if(this.value().equals(comp.value())) {
			return new Composite(this.value());
		}
		Node thisNode = this.head;
		Node otherNode = comp.head;
		if (this.head.factor == 1 || comp.head.factor == 1) {
			return new Composite(new BigInteger("" + 1));

		}
		long gcd = 1;
		while (thisNode.next != null) {
			long thisSmallest = thisNode.factor;
			boolean done =false;
			while (otherNode.next != null && !done) {
				long otherSmallest = otherNode.factor;
				if (thisSmallest == otherSmallest) {
					int power =1;
					if(thisNode.power==otherNode.power) {
						power = thisNode.power;
					}
					else if(thisNode.power<otherNode.power) {
						power = thisNode.power;
					}
					else {
						power = otherNode.power;
					}
					gcd *= Math.pow(thisSmallest, power);
					done =true;
				} else {
					otherNode = otherNode.next;
				}
			}
			otherNode = comp.head;
			thisNode = thisNode.next;
		}
		return new Composite(gcd);
	}

	/**
	 * Computes greatest common divisor with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Least_common_multiple
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in self and
	 * M is the number of factors in comp.
	 * 
	 * The least common multiple of (comp,1) is comp.
	 * 
	 * @param comp other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite lcm(Composite comp) {

		Node thisNode = this.head;
		Node otherNode = comp.head;
		if (this.head.factor == 1) {
			return comp;

		}
		else if (comp.head.factor==1) {
			return this;

		}
		HashMap<Long,Integer> factorMap = new HashMap<Long,Integer>();
		long lcm = 1;
		while (thisNode != null) {
			long factor = thisNode.factor;
			Integer power = thisNode.power;
			if(factorMap.get(factor)==null) {
				factorMap.put(factor, power);
			}
			else if(factorMap.get(factor)!=null && factorMap.get(factor).compareTo(power)<0) {
				factorMap.put(factor,power);
			}
			thisNode = thisNode.next;
		}
		while (otherNode!= null) {
			long factor = otherNode.factor;
			Integer power = otherNode.power;
			if(factorMap.get(factor)==null) {
				factorMap.put(factor, power);
			}
			else if(factorMap.get(factor)!=null && factorMap.get(factor).compareTo(power)<0) {
				factorMap.put(factor,power);
			}
			otherNode = otherNode.next;
		}
		for(Map.Entry<Long, Integer> entry: factorMap.entrySet()) {
			Long factor = entry.getKey();
			Integer power = entry.getValue();
			lcm *= Math.pow(factor, power);
		}
		return new Composite(lcm);
	}

	/** Helper value for factorize. */
	public final static BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

	/*
	 * static Stack<Long> factorize(long n) { Stack<Long> nums = new Stack<Long>();
	 * 
	 * for(int i = 2; i <= n; i++) { while(n %i ==0) { nums.push((long) i); n = n/i;
	 * } } nums = reverse(nums); System.out.println(nums);
	 * 
	 * return nums; }
	 */
	/**
	 * Insert the provided Long at the bottom of the stack, for reversal
	 * 
	 * @param list
	 * @param num
	 */
	private static void insertAtBottom(Stack<Long> list, Long num) {
		if (list.size() > 0) {
			Long temp = list.peek();
			list.pop();
			insertAtBottom(list, num);
			list.push(temp);
		} else {
			list.push(num);
		}
	}

	/**
	 * Reverse the stack
	 * 
	 * @param list
	 * @return
	 */
	private static Stack<Long> reverse(Stack<Long> list) {

		if (list.size() > 0) {
			Long first = list.peek();
			list.pop();
			reverse(list);
			insertAtBottom(list, first);

		}
		return list;
	}

	/**
	 * Return Composite number with linked list of factors in ascending order.
	 * 
	 * @param num
	 * @return
	 */
	public static Composite factorize(BigInteger num) {
		Composite number = new Composite();
		number.head = new Node(0, 0);
		// System.out.println("Current number: " +number);
		Node current = number.head;
		Stack<Long> nums = new Stack<Long>();
		long n = num.longValue();

		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				nums.push((long) i);
				n = n / i;
			}
		}
		nums = reverse(nums);
		BigInteger factor = null;
		if(nums.size()==0) {
			number.head.factor=1;
			number.head.power=1;
			number.head.next=new Node(num.longValue(),1);
			return number;
		}
		BigInteger oldFactor = new BigInteger("" + nums.pop());
		int power = 1;
		// for(int i =0; i < nums.size();i++) {
		// If the stack still has elements
		if(nums.size()==0 && oldFactor!=null) {
			number.head.factor=oldFactor.longValue();
			number.head.power=1;
			return number;
		}
		while (nums.size() > 0) {
			// Set factor to the next factor in the stack
			factor = new BigInteger("" + nums.pop());
			// If this is a duplicate, add another to power
			if (factor.longValue() == oldFactor.longValue()) {
				power++;

			} else {

				current.factor = oldFactor.longValue();

				current.power = power;
				power = 1;
				current.next = new Node(0, 0);
				current = current.next;
				oldFactor = factor;
			}
			if (nums.size() == 0) {
				current.factor = oldFactor.longValue();

				current.power = power;
				power = 1;
			}
		}
		// }

		return number;
	}
}
