package jemushatt.hw4;

import java.math.BigInteger;

import edu.princeton.cs.algs4.Stack;

/**
 * Homework 4: Data Type Exercise
 *
 * Copy this class into your USERID.hw4 package and complete.
 *
 * Each composite number is to be represented as a binary tree of prime factors (with value as power).
 *
 * Yes, the Composite Problem is Back!
 */
public class Composite {

	/**
	 * Keep track of the AVL tree of factor/exponents based at this root.
	 *
	 * Each key is a BigInteger factor; each value is a power of that factor
	 */
	AVL<BigInteger, Integer> tree = new AVL<BigInteger, Integer>();

	/**
	 * Constructs a Composite with the specified value, which may not be one, zero or negative.
	 */
	public Composite(long val) {
		this (BigInteger.valueOf(val));
	}

	/**
	 * Constructs a Composite from the given Pair<BigInteger,Integer> powers.
	 *
	 * Useful to be able to create a Composite object from pre-existing factors and exponents
	 */
	public Composite(Iterable<Pair<BigInteger,Integer>> factors) {
		for (Pair<BigInteger,Integer> pair : factors) {
			tree.put(pair.key, pair.value);
		}
	}

	/** Helper value for factorize. */
	public final static BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

	/**
	 * Constructs a Composite with the specified value, which may not be zero, one or negative.
	 *
	 */
	public Composite(BigInteger val) {
		if (val.compareTo(BigInteger.ZERO) <= 0) {
			throw new IllegalArgumentException ("Composite must be a non-negative value.");
		}
		if (val.compareTo(BigInteger.ONE) == 0) {
			throw new IllegalArgumentException ("Composite cannot be one.");
		}
		else {
			// System.out.println("Current number: " +number);
			Stack<Long> nums = new Stack<Long>();
			long n = val.longValue();

			for (int i = 2; i <= n; i++) {
				while (n % i == 0) {
					nums.push((long) i);
					n = n / i;
				}
			}
			nums = reverse(nums);
			BigInteger factor =null;
			//If stack still has elements to process
			while(nums.size()!=0) {
				//get next element on the stack
				factor=new BigInteger(""+nums.pop());
				//If element is already in the tree
				if(this.tree.get(factor) != null) {
					int power = this.tree.get(factor);
					power++;
					this.tree.put(factor, power);
				}
				else {
					this.tree.put(factor, 1);
				}
			}
		}
		// FILL IN HERE...
	}

	/**
	 * Returns string representation as a sequence of factors with primes
	 *
	 * 125 returns "5^3"
	 * 36 returns "2^2 * 3^2"
	 *
	 * Note that spaces appear between the * and the other factors
	 */
	@Override
	public String toString() {
		String result = "";
		for(Pair pair : tree.pairs()) {
			if(!pair.value.equals(1)) {
				result+= pair.key+ "^"+pair.value+" * ";
			}
			else {
				result+= pair.key + " * ";
			}
		}
		/*
		while(tree.pairs().iterator().next()!=null ) {
			Pair<BigInteger,Integer> num = tree.pairs().iterator().next();

			if(tree.pairs().iterator().next()==null) {
				result+= num.key+"^"+num.value;
			}
			else {
				result+= num.key+ "^"+num.value+"*";
			}
		}
		*/
		result = result.substring(0,result.length()-3);
		return result;
	}

	/**
	 * Determine if two composite values are equal to each other.
	 *
	 * Hint: Since you can't know the structure of the respective AVL trees, you should
	 * get their respective pairs as an iterator
	 */
	@Override
	public boolean equals (Object o) {
		if (o == null) { return false; }

		if (o instanceof Composite) {
			Composite other = (Composite) o;
			if(this.value().equals(((Composite) o).value())) {
				return true;
			}
			else {
				return false;
			}
		}

		return false;
	}

	/**
	 * Return value of Composite as a single BigInteger.
	 *
	 * Useful for testing.
	 *
	 * @return  a single BigInteger value representing actual value of Composite.
	 */
	public BigInteger value() {
		BigInteger val = new BigInteger(""+1);
		for(Pair<BigInteger,Integer> pair: tree.pairs()) {
			long term = pair.key.longValue();
			int factor = pair.value;
			term = (long) Math.pow(term, factor);
			val = new BigInteger(""+val.longValue()*term);
		}
		return val;
	}

	/**
	 * Determine if Composite represents a prime number.
	 *
	 * See https://en.wikipedia.org/wiki/Prime_number#Primality_of_one
	 */
	public boolean isPrime() {
		boolean isPrime = false;
		int count = 1;
		for(Pair<BigInteger,Integer> pair : tree.pairs()) {
			count*= pair.value;
		}
		if(tree.root.key.longValue()==1) {
			return false;
		}
		else if(tree.root.key.longValue()==2 && tree.root.value==1 && tree.height()==0) {
			return true;
		}
		else if(count>=2) {
			return false;
		}
		return isPrime;
	}

	/**
	 * Determine whether composite is simply divisible by prime number factor.
	 *
	 * You can assume factor is a prime number. If it is not, then this method is
	 * not responsible for the result.
	 */
	public boolean divisibleBy(long factor) {
		return divisibleBy(BigInteger.valueOf(factor));
	}

	/**
	 * Determine whether composite is simply divisible by prime number factor.
	 *
	 * You can assume factor is a prime number. If it is not, then this method is
	 * not responsible for the result.
	 */
	public boolean divisibleBy(BigInteger factor) {
		if (!factor.isProbablePrime(25)) {
			throw new IllegalArgumentException ("Factor is not prime:" + factor);
		}

		// FIX ME
		return false;
	}

	/**
	 * Computes product of two composite numbers.
	 *
	 * @param comp
	 * @return
	 */
	public Composite multiply(Composite comp) {
		if(comp.value().equals(1)) {
			return this;
		}
		else if(this.value().equals(1)) {
			return comp;
		}
		else {
			BigInteger sumThis = new BigInteger("1");
			BigInteger compThis = new BigInteger("1");
			for(Pair<BigInteger,Integer> pairs : this.tree.pairs()) {
				long term = pairs.key.longValue();
				int factor = pairs.value;
				BigInteger result  = new BigInteger("" +(long) Math.pow(term, factor));
				sumThis = sumThis.multiply(result);
			}
			for(Pair<BigInteger,Integer> pairs : comp.tree.pairs()) {
				long term = pairs.key.longValue();
				int factor = pairs.value;
				BigInteger result  = new BigInteger("" +(long) Math.pow(term, factor));
				compThis = compThis.multiply(result);
			}
			return new Composite(sumThis.multiply(compThis));
			/*
			BigInteger thisVal = this.value();
			BigInteger compVal = comp.value();
			BigInteger product = this.value().multiply(comp.value());
			return new Composite(product);
			*/
		}
	}

	/**
	 * Computes greatest common divisor with given composite number.
	 *
	 * https://en.wikipedia.org/wiki/Greatest_common_divisor
	 *
	 * When there is no common divisor (other than the value 1) this method returns null
	 *
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite gcd(Composite comp) {
		// REPLACE WITH WORKING CODE
		return null;
	}

	/**
	 * Computes least common multiple with given composite number.
	 *
	 * https://en.wikipedia.org/wiki/Least_common_multiple
	 *
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite lcm(Composite comp) {
		// REPLACE WITH WORKING CODE
		return null;
	}
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
	 */
	public static Composite factorize(BigInteger num) {

		Composite number = new Composite(num.longValue());
		number.tree.root = null;
		// System.out.println("Current number: " +number);
		Stack<Long> nums = new Stack<Long>();
		long n = num.longValue();

		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				nums.push((long) i);
				n = n / i;
			}
		}
		nums = reverse(nums);
		BigInteger factor =null;
		BigInteger oldFactor = new BigInteger(""+nums.pop());
		number.tree.put(oldFactor,1);
		//If stack still has elements to process
		if(nums.size()==0) {
			//get next element on the stack
			factor=new BigInteger(""+nums.pop());
			//If element is already in the tree
			if(number.tree.get(factor) != null) {
				int power = number.tree.get(factor);
				power++;
				number.tree.put(factor, power);
			}
		}
		return number;
	}
}
