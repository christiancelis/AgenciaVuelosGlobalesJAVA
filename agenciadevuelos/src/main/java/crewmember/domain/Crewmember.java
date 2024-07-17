package crewmember.domain;

public class Crewmember {
private int escalaId;
private int empleadoId;

public Crewmember() {
}
public Crewmember(int escalaId, int empleadoId) {
    this.escalaId = escalaId;
    this.empleadoId = empleadoId;
}
public int getEscalaId() {
    return escalaId;
}
public void setEscalaId(int escalaId) {
    this.escalaId = escalaId;
}
public int getEmpleadoId() {
    return empleadoId;
}
public void setEmpleadoId(int empleadoId) {
    this.empleadoId = empleadoId;
}

@Override
public String toString() {
    return "Crewmember [escalaId=" + escalaId + ", empleadoId=" + empleadoId + "]";
}

}
