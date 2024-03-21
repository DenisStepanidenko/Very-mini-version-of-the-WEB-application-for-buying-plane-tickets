import entity.User;
import validator.PossibleFormatForImage;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {
//        String fileName = "fdfdsfds.jpg";
//        int lastIndex = fileName.lastIndexOf('.');
//        String expansion = fileName.substring(lastIndex + 1);
//        System.out.println(expansion);
        String test1 = "jpg1";
        System.out.println(PossibleFormatForImage.valueOf(test1));
    }
}
