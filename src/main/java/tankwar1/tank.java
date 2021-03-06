package tankwar1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class tank {

    private int x ;

    private int y ;

    private boolean enemy;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private Direction direction;

    public tank(int x , int y , Direction direction) {
        this(x , y ,false , direction);

    }

    public tank(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    void move(){//移動的座標
        if(this.stopped){
            return;
        }
        switch (direction){
            case UP:
                y -= 5;
                break;
            case UPRIGHT:
                y -= 5;
                x += 5;
                break;
            case UPLEFT:
                y -= 5;
                x -= 5;
                break;
            case DOWN:
                y += 5;
                break;
            case DOWNLEFT:
                y += 5;
                x -= 5;
                break;
            case DOWNRIGHT:
                y += 5;
                x += 5;
                break;
            case LEFT:
                x -= 5;
                break;
            case RIGHT:
                x += 5;
                break;
        }
    }

    Image getImage(){//移動方向時,圖片變更
        String prefix =enemy ? "e" : "";
        switch (direction){
            case UP:return  new ImageIcon("assets/images/" + prefix +"tankU.gif").getImage();
            case UPLEFT:return  new ImageIcon("assets/images/" + prefix +"tankLU.gif").getImage();
            case UPRIGHT:return  new ImageIcon("assets/images/" + prefix +"tankRU.gif").getImage();
            case DOWN:return  new ImageIcon("assets/images/" + prefix +"tankD.gif").getImage();
            case DOWNLEFT:return  new ImageIcon("assets/images/" + prefix +"tankLD.gif").getImage();
            case DOWNRIGHT:return  new ImageIcon("assets/images/" + prefix +"tankRD.gif").getImage();
            case LEFT:return  new ImageIcon("assets/images/" + prefix +"tankL.gif").getImage();
            case RIGHT:return  new ImageIcon("assets/images/" + prefix +"tankR.gif").getImage();

            }
            return null;
        }

        void draw(Graphics g){//設定整體的移動,座標,圖片
            this.determineDirection();
            this.move();
            g.drawImage(this.getImage(),
                    this.x , this.y , null);
        }

        private boolean up,down,left,right;
        public void keyPressed(KeyEvent e){//輸入鍵位時，獲取相應的位置
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
            }
            this.determineDirection();
            this.move();
        }
        private boolean stopped ;

        private void determineDirection(){
            if (!up && !right && ! down && !left){
                this.stopped = true;
            }else {
                if (up && left && !down && !right) this.direction = Direction.UPLEFT;
                else if (up && right && !down && !left) this.direction = Direction.UPRIGHT;
                else if (down && left && !up && !right) this.direction = Direction.DOWNLEFT;
                else if (down && right && !up && !left) this.direction = Direction.DOWNRIGHT;
                else if (up && !right && !down && !left) this.direction = Direction.UP;
                else if (!up && !right && down && !left) this.direction = Direction.DOWN;
                else if (!up && !right && !down && left) this.direction = Direction.LEFT;
                else if (!up && right && !down && !left) this.direction = Direction.RIGHT;

                this.stopped = false ;
            }
        }

        public void keyReleased(KeyEvent e){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
            }
            this.determineDirection();
        }

}

