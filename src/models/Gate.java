package models;

import models.enums.GateStatus;
import models.enums.GateType;

public class Gate {
    private Long id;
    private int gateNumber;
    private GateType gateType;
    private Operator operator;
    private GateStatus gateStatus;

    public Gate(Long id, int gateNumber, GateType gateType, Operator operator){
        this.id = id;
        this.gateNumber = gateNumber;
        this.gateType = gateType;
        this.operator = operator;
        this.gateStatus = GateStatus.OPERATIONAL;
    }

    public Long getId() {
        return id;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public GateType getGateType() {
        return gateType;
    }

    public Operator getOperator() {
        return operator;
    }

    public GateStatus getGateStatus() {
        return gateStatus;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }
}
