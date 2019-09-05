#include "longint.h"

/**
 * Your operator>> must read characters '0' through '9' from the keyboard as 
 * well as accept '-' if it is the first character. Skip all the other 
 * characters.
 * @param out output stream
 * @param rhs current Object
 * @return output stream
 */
ostream& operator<<(ostream &out, const LongInt &rhs) {
    LongInt outputTemp = rhs; //temporary LongInt to iterate through digits
    outputTemp.remove0s();

    if (rhs.negative) //outputs a - if the longInt is negative
        out << '-';
    while (!outputTemp.digits.isEmpty()) //outputs each digit of longInt
        out << outputTemp.digits.removeFront();

    return out;
}

/**
 * Your operator<< must print out a given LongInt object's digits as an integer.
 * If the LongInt object's digits is empty or 0, it should be printed out as 0 
 * without a negative sign. Wrong outputs include:
 * @param in input stream
 * @param rhs current Object
 * @return input stream
 */
istream& operator>>(istream &in, LongInt &rhs) {
    string lineIn;
    in >> lineIn;

    //loop through each character adding it to rhs if necessary
    for (int i = 0; i < lineIn.size(); i++) {
        //looks for '-' in first value
        if (lineIn[i] == '-' && i == 0)
            rhs.negative = true;
            //checks if input is 0 to 9
        else if (isdigit(lineIn[i]))
            rhs.digits.addBack(lineIn[i]);
    }

    rhs.remove0s(); //remove extra zeros
    return in;
}

/**
 * The destructor should deallocate all Deque items.
 */
LongInt::~LongInt() {
    digits.clear();
}

/**
 * the default constructor that initializes the object to 0
 */
LongInt::LongInt() {
    negative = false;
    digits.addFront('0');
}

/**
 * Constructor that reads a string to convert to a Deque
 * @param str String to be converted to Deque
 */
LongInt::LongInt(const string str) {
    if (str[0] == '-') //Checks if first char is negative
        negative = true;
    for (int i = 0; i < str.size(); i++) { //iterates through characters
        if (isdigit(str[i]))
            digits.addBack(str[i]);
    }
    remove0s();
}

/**
 * the copy constructor
 * @param rhs other LongInt
 */
LongInt::LongInt(const LongInt &rhs) {
    digits = rhs.digits;
    negative = rhs.negative;
    remove0s();
}

/**
 * Arithmetic Binary Operators that adds two LongInts together.
 * @param rhs other LongInt
 * @return sum of two LongInts
 */
LongInt LongInt::operator+(const LongInt &rhs) const {
    LongInt sum;
    sum.digits.clear(); //clear LongInt
    int carry = 0; //carry after each addition

    Deque<char> ls = digits; //left side
    Deque<char> rs = rhs.digits; //right side

    //checks if addition or subtraction is necessary
    if (negative == rhs.negative) {
        //handles (negative + negative) & (positive + positive)
        while (!ls.isEmpty()&&!rs.isEmpty()) {
            int lso = ls.removeBack() - '0'; //left side operand
            int rso = rs.removeBack() - '0'; //right side operand

            int localSum = lso + rso + carry; //adds values and carry 
            carry = localSum / 10; //identifies new carry

            sum.digits.addFront((localSum % 10) + '0'); //adds to sum
        }

        //takes care of remaining digits after initial addition
        while (!ls.isEmpty() || !rs.isEmpty()) {
            int leftOver;
            //finds which side has remaining digits
            if (ls.isEmpty())
                leftOver = rs.removeBack() - '0';
            else
                leftOver = ls.removeBack() - '0';

            int localSum = leftOver + carry; //adds value and carry
            int carry = localSum / 10; //identifies new carry

            sum.digits.addFront((localSum % 10) + '0'); //adds to sum
        }

        //takes care of remaining carry
        if (carry != 0) {
            sum.digits.addFront((carry % 10) + '0');
            carry = carry / 10;
        }

        //handles sum's sign
        if (negative) {
            sum.negative = true;
        } else {
            sum.negative = false;
        }
    } else if (!negative && rhs.negative) {
        //positive lhs + negative rhs means ans = lhs - rhs.
        LongInt tempSign = rhs;
        tempSign.negative = false;
        return (*this -tempSign);
    } else {
        //negative lhs + positive rhs means ans = rhs - lhs.
        LongInt tempSign = *this;
        tempSign.negative = false;
        return (rhs - tempSign);
    }

    sum.remove0s();
    return sum;
}

/**
 * Arithmetic Binary Operators that subtracts two LongInts together.
 * @param rhs other LongInt
 * @return difference of two LongInts
 */
LongInt LongInt::operator-(const LongInt &rhs) const {
    LongInt difference;
    difference.digits.clear(); //Clear LongInt
    Deque<char> ls; //left side
    Deque<char> rs; //right side

    // Special case when the result is equal to 0;
    if (*this == rhs) {
        difference.negative = false;
        difference.digits.addFront('0');
        return difference;
    }

    //checks if addition or subtraction is necessary
    if (!negative && rhs.negative) {
        //positive lhs - negative rhs means ans = lhs + rhs.
        LongInt tempSign = rhs;
        tempSign.negative = false;
        return (*this +tempSign);
    } else if (negative && !rhs.negative) {
        //negative lhs - positive rhs means ans = -(lhs + rhs).
        LongInt tempSign = rhs;
        tempSign.negative = true;
        return (*this +tempSign);
    } else if (negative && rhs.negative) {
        //negative lhs - negative rhs means ans = rhs - lhs.
        ls = rhs.digits;
        rs = digits;
    } else {
        //positive lhs - positive rhs means ans = lhs - rhs.
        ls = digits;
        rs = rhs.digits;
    }

    //swap so larger value is on left side
    if (*this < rhs) {
        difference.negative = true;
        Deque<char> temp = ls;
        ls = rs;
        rs = temp;
    }

    //subtract two LongInts
    int borrow = 0;
    while (!ls.isEmpty()&&!rs.isEmpty()) {
        int lso = ls.removeBack() - '0'; //left side operand
        int rso = rs.removeBack() - '0'; //right side operand
        int localDifference;

        //subtracts digits borrow if necessary
        if (lso < (rso + borrow)) {
            lso += 10;
            localDifference = lso - rso - borrow;
            borrow = 1;
        } else {
            localDifference = lso - rso - borrow;
            borrow = 0;
        }

        difference.digits.addFront(localDifference + '0'); //insert difference
    }

    //takes care of remaining digits after initial subtraction
    while (!ls.isEmpty() || !rs.isEmpty()) {
        int leftOver;
        //finds which side has remaining digits
        if (ls.isEmpty())
            leftOver = rs.removeBack() - '0';
        else
            leftOver = ls.removeBack() - '0';

        int localDifference = leftOver - borrow;
        borrow = 0;
        difference.digits.addFront(localDifference + '0'); //insert difference
    }

    difference.remove0s();
    return difference;
}

/**
 * Assignment operator. Sets current equal to other.
 * 
 * @param rhs other LongInt
 * @return new current
 */
const LongInt& LongInt::operator=(const LongInt& rhs) {
    //copy digits and negative 
    digits = rhs.digits;
    negative = rhs.negative;

    return *this;
}

/**
 * Compare two LongInts, return true is current is greater than other.
 * @param rhs other
 * @return True if current greater than other
 */
bool LongInt::operator>(const LongInt & rhs) const {
    Deque<char> current = digits;
    Deque<char> other = rhs.digits;


    //if current is equal to other return false
    if (*this == rhs) {
        return false;
    }

    //iterate through digits starting at most significant
    while (!current.isEmpty()&&!other.isEmpty()) {
        int x = current.removeFront() - '0';
        int y = other.removeFront() - '0';
        if (negative)
            x *= -1;
        if (rhs.negative)
            y *= -1;

        //comparison of significant digits
        if (x > y)
            return true;
        if (x < y)
            return false;
    }

    //if current has less significant digits it is not greater 
    if (current.isEmpty() && !other.isEmpty())
        return false;
    else
        return true;
}

/**
 * Compares two LongInts, returns true if current is less than or equal to 
 * other.
 * @param rhs other
 * @return  True if current less than or equal to other
 */
bool LongInt::operator<=(const LongInt& rhs) const {
    return !(*this > rhs);
}

/**
 * Compare two LongInts, return true is current is less than other.
 * @return True if current less than other
 */
bool LongInt::operator<(const LongInt& rhs) const {
    Deque<char> current = digits;
    Deque<char> other = rhs.digits;


    //if current is equal to other return false
    if (*this == rhs) {
        return false;
    }

    //iterate through digits starting at most significant
    while (!current.isEmpty()&&!other.isEmpty()) {
        int x = current.removeFront() - '0';
        int y = other.removeFront() - '0';
        if (negative)
            x *= -1;
        if (rhs.negative)
            y *= -1;

        //comparison of significant numbers
        if (x > y)
            return false;
        if (x < y)
            return true;
    }

    //if current has more significant digits it is not less than
    if (!current.isEmpty() && other.isEmpty())
        return false;
    else
        return true;
}

/**
 * Compares two LongInts, returns true if current is greater than or equal to 
 * other.
 * @param rhs other
 * @return  True if current greater than or equal to other
 */
bool LongInt::operator>=(const LongInt & rhs) const {
    return !(*this < rhs);
}

/**
 * Compares two LongInts, returns true if current is equal to other
 * @param rhs
 * @return True if current is equal to other
 */
bool LongInt::operator==(const LongInt & rhs) const {
    Deque<char> current= digits;
    Deque<char> other= rhs.digits;
    

    if (negative != rhs.negative)
        return false;

    //iterate through digits starting at most significant
    while (!current.isEmpty()&&!other.isEmpty()) {
        int x = current.removeFront() - '0';
        int y = other.removeFront() - '0';
        if (negative)
            x *= -1;
        if (rhs.negative)
            y *= -1;

        //compares digits
        if (x != y)
            return false;
    }

    //compare remaining (size)
    if (!current.isEmpty() != !other.isEmpty())
        return false;
    else
        return true;
}

/**
 * Compares two LongInts, returns true if current is not equal to other
 * @param rhs
 * @return True if current is not equal to other
 */
bool LongInt::operator!=(const LongInt & rhs) const {
    return !(*this == rhs);
}

/**
 * Remove all extra zeros from the beginning of a LongInt. -0003450 will become
 * -3450, 000 will become 0.
 */
void LongInt::remove0s() {
    bool moreZeros = true;
    //removes all unnecessary zeros
    while (!digits.isEmpty() && moreZeros) {
        if (digits.getFront() == '0') {
            digits.removeFront();
        } else {
            moreZeros = false;
        }
    }
    //adds 0 to empty array
    if (digits.isEmpty()) {
        digits.addFront('0');
        negative = false;
    }
    return;

}