package utils;

import jutlis.lists.ListArray;

/**
 *
 * @author kommusoft
 */
public class TestData {

    public static final Integer[] vals1 = new Integer[]{0x02, -0x02, 0x00, 0x04, Integer.MIN_VALUE, 0x05, 0x07, Integer.MAX_VALUE};
    public static final ListArray<Integer> vals1le = new ListArray<>(new Integer[]{0x02, -0x02, 0x00, Integer.MIN_VALUE});
    public static final ListArray<Integer> vals1geq = new ListArray<>(new Integer[]{0x04, 0x05, 0x07, Integer.MAX_VALUE});
    public static final Integer split1 = 0x04;
    public static final String[] vals2 = new String[]{null, "zzz", "kommusoft", "ape", "monkey business", "kommu", "kola", "kommusoftz"};
    public static final ListArray<String> vals2geq = new ListArray<>(new String[]{"zzz", "kommusoft", "monkey business", "kommusoftz"});
    public static final ListArray<String> vals2le = new ListArray<>(new String[]{null, "ape", "kommu", "kola"});
    public static final String split2 = "kommusoft";

    private TestData() {
    }

}
