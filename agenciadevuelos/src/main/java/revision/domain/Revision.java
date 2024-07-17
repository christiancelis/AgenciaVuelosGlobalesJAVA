package revision.domain;

import java.sql.Date;

public class Revision {
private int id;
private Date fechaRevision;
private int avionId;


public Revision() {
}
public Revision(int id, Date fechaRevision, int avionId) {
    this.id = id;
    this.fechaRevision = fechaRevision;
    this.avionId = avionId;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public Date getFechaRevision() {
    return fechaRevision;
}
public void setFechaRevision(Date fechaRevision) {
    this.fechaRevision = fechaRevision;
}
public int getAvionId() {
    return avionId;
}
public void setAvionId(int avionId) {
    this.avionId = avionId;
}
@Override
public String toString() {
    return "Revision [id=" + id + ", fechaRevision=" + fechaRevision + ", avionId=" + avionId + "]";
}



}
