package frc.robot.POM_lib.Vision.ObjectDetection;

public class ObjectDetectionConstants {
    enum GameObjects {
        NOTE(0);
        int id;
        private GameObjects(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
        public static GameObjects getFromId(int num) {
            switch(num)
            {
                case 0:
                    return NOTE;
                default:
                    return NOTE;
            }
        }
    }
}
