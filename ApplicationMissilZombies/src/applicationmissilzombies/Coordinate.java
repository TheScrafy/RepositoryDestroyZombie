/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmissilzombies;

/**
 *
 * @author SHIAN
 */
public class Coordinate {

    private Integer posX;
    private Integer posY;
    private Integer cantZombie;

    public Coordinate() {
    }

    public Coordinate(Integer posX, Integer posY, Integer cantZombie) {
        this.posX = posX;
        this.posY = posY;
        this.cantZombie = cantZombie;
    }

    public Coordinate(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getCantZombie() {
        return cantZombie;
    }

    public void setCantZombie(Integer cantZombie) {
        this.cantZombie = cantZombie;
    }

}
