package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 12;
        direction = "right";
    }

    public void getPlayerImage(){

        try{
            up0 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/up0.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/up1.png")));
            down0 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/down0.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/down1.png")));
            left0 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/left0.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/left1.png")));
            right0 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/right0.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/right1.png")));

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed){
                direction = "down";
                y += speed;
            }
            else if(keyH.leftPressed){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed){
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > gp.FPS/3){
                if(spriteNum == 0){
                    spriteNum=1;
                } else if (spriteNum == 1) {
                    spriteNum=0;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 0) image = up0;
                if (spriteNum == 1) image = up1;
            }
            case "down" -> {
                if (spriteNum == 0) image = down0;
                if (spriteNum == 1) image = down1;
            }
            case "left" -> {
                if (spriteNum == 0) image = left0;
                if (spriteNum == 1) image = left1;
            }
            case "right" -> {
                if (spriteNum == 0) image = right0;
                if (spriteNum == 1) image = right1;
            }
            default -> {
            }
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }

}
