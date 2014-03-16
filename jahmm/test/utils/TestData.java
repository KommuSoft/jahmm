package utils;

import jahmm.jadetree.foo.Test2B1T;
import jahmm.jadetree.foo.TrisEnum;
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
    public static final String name1 = "value";
    public static final String[] vals2 = new String[]{null, "zzz", "kommusoft", "ape", "monkey business", "kommu", "kola", "kommusoftz"};
    public static final ListArray<String> vals2geq = new ListArray<>(new String[]{"zzz", "kommusoft", "monkey business", "kommusoftz"});
    public static final ListArray<String> vals2le = new ListArray<>(new String[]{null, "ape", "kommu", "kola"});
    public static final String split2 = "kommusoft";
    public static final String name2 = "username";
    public static final Test2B1T[] vals3 = new Test2B1T[]{new Test2B1T(false, false, TrisEnum.Odin), new Test2B1T(false, true, TrisEnum.Dva), new Test2B1T(true, false, TrisEnum.Tri), new Test2B1T(true, true, TrisEnum.Odin)};
    public static final ListArray<Test2B1T> vals3a = new ListArray<>(new Test2B1T(false, false, TrisEnum.Odin), new Test2B1T(true, true, TrisEnum.Odin));
    public static final ListArray<Test2B1T> vals3b = new ListArray<>(new Test2B1T(false, true, TrisEnum.Dva));
    public static final ListArray<Test2B1T> vals3c = new ListArray<>(new Test2B1T(true, false, TrisEnum.Tri));

    private TestData() {
    }

}
