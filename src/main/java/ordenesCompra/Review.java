/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenesCompra;

import java.util.Date;

/**
 *
 * @author hduser
 */
class Review {

    private Date fecha;
    private Usuario cutomer;
    private long rating;
    private long votes;
    private long helpful;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getCutomer() {
        return cutomer;
    }

    public void setCutomer(Usuario cutomer) {
        this.cutomer = cutomer;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public long getHelpful() {
        return helpful;
    }

    public void setHelpful(long helpful) {
        this.helpful = helpful;
    }

}
