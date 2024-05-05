import top.abmcar.earyproduction.utils.StringUtil;

public class StringUtilsTest {
    public static void main(String[] args) {
        assert StringUtil.registryNameToUnlocalizedName("tempBlock").equals("temp_block");
        assert StringUtil.registryNameToUnlocalizedName("temp").equals("temp");
        System.out.println(StringUtil.registryNameToUnlocalizedName("temp_block"));
    }
}
