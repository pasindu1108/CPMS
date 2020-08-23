/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author madub
 */
class User {
    
    
    private String LeaveType,LeaveDate,Duration,EID,Approval;
    
    public User(String EID,String LeaveDate,String LeaveType,String Duration,String Approval){
        
        this.Duration=Duration;
        this.EID=EID;
        this.LeaveDate=LeaveDate;
        this.LeaveType=LeaveType;
        this.Approval=Approval;
        
    }

    public String getApproval() {
        return Approval;
    }

    public String getLeaveType() {
        return LeaveType;
    }

    public String getLeaveDate() {
        return LeaveDate;
    }

    public String getDuration() {
        return Duration;
    }

    public String getEID() {
        return EID;
    }
    
    
    
}

