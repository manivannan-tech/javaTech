

/*
We are building a program to manage a gym's membership. The gym has multiple members, each with a unique ID, name, and membership status. The program allows gym staff to add new members, update members status, and get membership statistics.

Definitions:
* A "member" is an object that represents a gym member. It has properties for the ID, name, and membership status.
* A "membership" is a class which is used for managing members in the gym.

To begin with, we present you with two tasks:
1-1) Read through and understand the code below. Please take as much time as necessary, and feel free to run the code.
1-2) The test for Membership is not passing due to a bug in the code. Make the necessary changes to Membership to fix the bug.
*/
package javacode;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

enum MembershipStatus {
    /*
        Membership Status is of three types: BRONZE, SILVER and GOLD.
        BRONZE is the default membership a new member gets.
        SILVER and GOLD are paid memberships for the gym.
    */
    BRONZE,
    SILVER,
    GOLD
}

class Member {
    /* Data about a gym member.*/
    public int memberId;
    public String name;
    public MembershipStatus membershipStatus;

    public Member(int memberId, String name, MembershipStatus membershipStatus) {
        this.memberId = memberId;
        this.name = name;
        this.membershipStatus = membershipStatus;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name + ", Membership Status: " + membershipStatus;
    }
}

class Membership {
    /*
        Data for managing a gym membership, and methods which staff can
        use to perform any queries or updates.
    */
    public List<Member> members;

    public Membership() {
        members = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void updateMembership(int memberId, MembershipStatus membershipStatus) {
        for (Member member : members) {
            if (member.memberId == memberId) {
                member.membershipStatus = membershipStatus;
                break;
            }
        }
    }

    public MembershipStatistics getMembershipStatistics() {
        int totalMembers = members.size();
        int totalPaidMembers = 0;
        for (Member member : members) {
            if (member.membershipStatus == MembershipStatus.SILVER) {
                totalPaidMembers++;
            }
        }
        double conversionRate = (totalPaidMembers / (double) totalMembers) * 100.0;
        return new MembershipStatistics(totalMembers, totalPaidMembers, conversionRate);
    }
}

class MembershipStatistics {
    /*
        Class for returning the getMembershipStatistics result
    */
    public int totalMembers;
    public int totalPaidMembers;
    public double conversionRate;

    public MembershipStatistics(int totalMembers, int totalPaidMembers, double conversionRate) {
        this.totalMembers = totalMembers;
        this.totalPaidMembers = totalPaidMembers;
        this.conversionRate = conversionRate;
    }
}

public class test {
    /*
        This is not a complete test suite, but tests some basic functionality of
        the code and shows how to use it.
    */
    public static void main(String[] args) {
        testMember();
        testMembership();
    }

    public static void testMember() {
        System.out.println("Running testMember");
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        assert testMember.memberId == 1 : 
            "member ID should be 1, was " + testMember.memberId;
        assert testMember.name.equals("John Doe") : 
            "member name should be \"John Doe\", was \"" + testMember.name + "\"";
        assert testMember.membershipStatus == MembershipStatus.BRONZE : 
            "membership status should be BRONZE, was " + testMember.membershipStatus;
    }

    public static void testMembership() {
        System.out.println("Running testMembership");
        Membership testMembership = new Membership();
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        testMembership.addMember(testMember);
        assert testMembership.members.size() == 1 : 
            "members size should be 1, was " + testMembership.members.size();
        assert testMembership.members.get(0).equals(testMember) : 
            "first member should equal testMember";

        testMembership.updateMembership(1, MembershipStatus.SILVER);
        assert testMembership.members.get(0).membershipStatus == MembershipStatus.SILVER : 
            "membership status should be SILVER, was " + testMembership.members.get(0).membershipStatus;

        Member testMember2 = new Member(2, "Alex C", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Member testMember3 = new Member(3, "Marie C", MembershipStatus.GOLD);
        testMembership.addMember(testMember3);

        Member testMember4 = new Member(4, "Joe D", MembershipStatus.SILVER);
        testMembership.addMember(testMember4);

        Member testMember5 = new Member(5, "June R", MembershipStatus.BRONZE);
        testMembership.addMember(testMember5);
 
        MembershipStatistics attendanceStats = testMembership.getMembershipStatistics();
        assert attendanceStats.totalMembers == 5 : 
            "total members should be 5, was " + attendanceStats.totalMembers;
         System.out.println("THE VALUE "+attendanceStats.totalPaidMembers);
        assert attendanceStats.totalPaidMembers == 3 : 
            "total paid members should be 3, was " + attendanceStats.totalPaidMembers;
        assert Math.abs(attendanceStats.conversionRate - 60.00) < 0.1 : 
            "conversion rate should be 60.00, was " + attendanceStats.conversionRate;
    }
}


