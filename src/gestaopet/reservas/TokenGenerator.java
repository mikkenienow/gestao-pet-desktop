package gestaopet.reservas;

import gestaopet.classes.DateTools;
import gestaopet.enums.DateMethods;
import java.util.Random;

public class TokenGenerator {
    private static Random r = new Random();
    private static String[][] keys;/* = {
        {"by", "TG", "tq", "To", "oo", "Nj", "UH", "OV", "dV", "wM", "Ob", "rv", "rP", "bw", "Wf", "Pb", "mA", "jS", "wc", "uw", "Rb", "OP", "fm", "En", "cz", "Kb", "jH", "ru", "bS", "GJ", "Cu", "pM", "oA", "Yy", "gB", "ls", "yG", "gf", "lM", "kN", "Nu", "YW", "Jk", "Nq", "RT", "ZP", "ZZ", "Gl", "mE", "Mg", "oE", "Dj", "FT", "EX", "MN", "Hp", "sW", "ob", "dn", "dp", "Nd", "oM", "Qc", "eX", "rF", "nF", "kI", "lT", "aK", "wD", "Ce", "TR", "AU", "VM", "yV", "kk", "ka", "wp", "qt", "Su", "vG", "Dw", "tY", "Ad", "VV", "RY", "Yl", "Px", "pk", "od", "wV", "vv", "be", "pg", "UX", "sq", "AF", "JQ", "Qd", "xQ"},
        {"NM", "Bz", "rY", "xB", "Fj", "Wv", "Jg", "hu", "Yd", "jZ", "bw", "hj", "Qo", "tB", "Kv", "jj", "we", "Za", "Ed", "ZU", "Vs", "pz", "VL", "UY", "Iw", "NS", "kZ", "CF", "pB", "Xo", "gx", "nb", "sA", "lj", "Xs", "wm", "HE", "uA", "fj", "Ow", "VR", "Uf", "DZ", "rc", "dq", "Ca", "Zo", "up", "UZ", "ck", "UY", "bz", "HT", "GL", "iP", "dn", "Hy", "AO", "WK", "rZ", "PP", "aD", "vj", "jt", "qK", "rT", "wU", "du", "nf", "XM", "jI", "Tu", "Dx", "jc", "CH", "qM", "nc", "Mt", "pD", "VD", "Ln", "be", "iL", "Hm", "zH", "dz", "Nh", "zI", "wS", "tn", "dy", "FS", "gk", "Vf", "OU", "UP", "de", "bL", "tI", "Cl"},
        {"uP", "ZI", "XT", "Tu", "pH", "Yv", "GF", "CK", "XF", "bE", "zS", "Il", "fL", "tC", "wg", "TJ", "Oe", "ic", "GC", "Yb", "HW", "jk", "QH", "RE", "yA", "ck", "zk", "BL", "nO", "qp", "uD", "Ly", "KN", "sJ", "Im", "Qv", "qk", "Ee", "aQ", "Ii", "sS", "CI", "mF", "eX", "AG", "Gy", "Vm", "Df", "nd", "Qj", "jL", "of", "UI", "bW", "aM", "Hl", "ry", "gj", "Oo", "BE", "yz", "KC", "Tn", "ek", "yv", "jn", "JW", "ao", "aK", "rx", "ZY", "fI", "OK", "UD", "wk", "PU", "ww", "Kg", "Fj", "Dd", "PX", "iK", "fj", "rK", "Xl", "fk", "ui", "Eh", "aV", "fO", "Uq", "am", "wm", "Qx", "Pp", "rr", "Lp", "GA", "eb", "lo"},
        {"IY", "MT", "Ay", "DD", "nn", "UT", "XN", "qk", "mn", "xe", "QW", "Kq", "ea", "AH", "jX", "oD", "Kb", "mE", "Bn", "Gr", "lw", "wx", "Zr", "DF", "OZ", "yt", "Tw", "iH", "qv", "cC", "vn", "hv", "TE", "zv", "pA", "wD", "Ns", "ul", "bc", "eA", "Oj", "QM", "xA", "ep", "Hw", "nz", "dl", "Lt", "cB", "Kr", "ID", "YA", "FL", "Is", "PV", "Xs", "GP", "Wf", "WK", "Ul", "vN", "Wp", "na", "xS", "re", "Yl", "wb", "Ed", "Hr", "Wi", "Eh", "NO", "Mn", "mm", "OA", "Ky", "rX", "Fl", "ZQ", "ou", "Cl", "bR", "Au", "fh", "MY", "yR", "lJ", "RK", "Lx", "Db", "fy", "aF", "Mw", "dL", "nG", "ZB", "uY", "ro", "Uv", "CB"},
        {"Nk", "nV", "gU", "sj", "lo", "di", "BT", "GR", "ki", "Ua", "dZ", "BK", "Xr", "gR", "TW", "la", "fI", "Sg", "uk", "nk", "XT", "ty", "ni", "Gb", "Bx", "HC", "Qv", "ok", "Xf", "pu", "Ak", "ek", "iw", "iX", "RK", "xX", "Lb", "rE", "CO", "xj", "uu", "VW", "uH", "bH", "nY", "Ik", "fb", "Lj", "Gs", "XF", "Jt", "Vv", "lF", "aG", "Uc", "fq", "Kq", "TH", "ce", "YQ", "xs", "nZ", "Dl", "YR", "kv", "or", "iI", "Ms", "kp", "Eo", "yE", "Pq", "fg", "ZX", "fV", "dE", "kq", "Xc", "Fo", "ch", "RB", "yd", "CU", "WJ", "ha", "LR", "AL", "Ax", "oG", "Zz", "pJ", "hF", "jp", "HV", "ya", "bw", "Do", "gU", "eb", "pf"},
        {"px", "Ea", "hf", "fE", "Pe", "KM", "as", "fV", "xn", "qg", "Ku", "yi", "uQ", "Ul", "Ao", "kA", "PN", "Zn", "kb", "Zn", "pc", "Mu", "de", "gq", "vc", "De", "Ld", "QI", "ay", "oH", "em", "aK", "IT", "ly", "oB", "wX", "uD", "ls", "qv", "TT", "Pw", "jM", "wH", "bp", "PK", "lq", "Mc", "Ux", "KR", "mV", "hk", "lH", "Ti", "ek", "cS", "HQ", "Mb", "IG", "Zh", "fU", "Pc", "bd", "Tz", "iy", "ZC", "eG", "IR", "SP", "mk", "rq", "ra", "Qw", "XR", "aD", "bl", "Al", "zI", "WJ", "Lw", "ee", "fp", "JT", "Wa", "Aw", "sz", "MV", "jG", "Ji", "NJ", "hg", "Zi", "ut", "hS", "Qj", "gj", "KF", "fP", "lu", "sC", "Fa"},
        {"xs", "BA", "UG", "fr", "UE", "mP", "El", "Jp", "lA", "zw", "ib", "SD", "SY", "Yw", "CC", "yO", "nm", "fR", "Zf", "Hb", "Sj", "uC", "yo", "lq", "tx", "VM", "bm", "kT", "wj", "bO", "Qq", "UM", "JI", "CN", "yX", "IT", "JA", "Ib", "QM", "Li", "Kn", "oT", "dv", "tV", "fX", "wC", "Ej", "cK", "YG", "GS", "FG", "gX", "ZB", "Wy", "JN", "UH", "aD", "pr", "GT", "Xu", "Zc", "QK", "Gj", "Bo", "gZ", "zl", "Pt", "Mx", "hU", "rx", "kf", "tx", "Bd", "RY", "FU", "Lm", "df", "LM", "YU", "oH", "GN", "un", "ge", "am", "qZ", "Eh", "pX", "Hc", "hE", "eB", "uv", "Pb", "lw", "KY", "Jf", "Bl", "mv", "Yy", "SL", "sv"},
        {"ZQ", "SU", "EL", "jV", "fx", "GK", "FO", "rv", "ne", "Js", "XQ", "Nl", "VE", "zU", "Lj", "xe", "BT", "fa", "Mh", "BM", "vd", "ly", "Cz", "in", "ah", "jl", "Xm", "wp", "Ql", "LY", "Yw", "AG", "ti", "iU", "to", "AU", "UJ", "kh", "Zv", "lw", "km", "yB", "hJ", "tW", "HE", "Wi", "lB", "Yy", "zA", "OY", "zz", "iJ", "nX", "II", "Gf", "zn", "RD", "Ga", "dA", "KU", "iK", "RF", "vX", "Xi", "fe", "Ar", "Yd", "RP", "Iz", "cT", "lx", "oL", "qw", "Or", "qD", "DV", "nA", "KW", "xg", "gX", "Qw", "nu", "hs", "AU", "ZZ", "cT", "of", "ZH", "vm", "Ah", "mw", "Ur", "Jg", "JK", "au", "Uw", "Bp", "ZK", "gE", "yh"},
        {"Ri", "ui", "Ap", "Xu", "Pa", "Eo", "Yr", "dA", "ci", "Yl", "ri", "kJ", "lr", "QK", "xy", "GH", "KU", "Yd", "pJ", "UP", "CE", "Ea", "qx", "gT", "tz", "yX", "Op", "qs", "ya", "Sv", "kz", "WY", "kI", "aQ", "XR", "cS", "XG", "DO", "of", "zw", "jn", "aY", "Jv", "Vz", "Cp", "NL", "Xo", "zv", "PH", "ZX", "mF", "QD", "SA", "QM", "zn", "Kj", "wk", "Di", "gL", "Ak", "LZ", "NM", "xk", "EJ", "uY", "JT", "bh", "UF", "iI", "Ow", "vU", "qM", "OI", "vk", "iN", "Jx", "AK", "tI", "BI", "Hu", "Ld", "es", "Qw", "Mo", "oW", "Xz", "Ls", "zI", "Lb", "ql", "OQ", "YJ", "Ik", "tb", "gh", "GL", "Md", "aV", "OB", "en"},
        {"oZ", "hH", "pQ", "CL", "yQ", "Ht", "Wx", "li", "fh", "GD", "wx", "qx", "Nl", "Ur", "Lm", "vR", "ZA", "et", "Zw", "uS", "ij", "qC", "qb", "aO", "TM", "Xp", "eW", "vh", "sw", "Kn", "HH", "wn", "CT", "pQ", "OW", "Bw", "Wa", "KF", "jq", "uV", "AE", "ZH", "Yo", "PH", "Qu", "IV", "ov", "Lv", "HF", "Wj", "rU", "Td", "YK", "Kf", "MD", "dw", "BU", "Eu", "WC", "sf", "WO", "yk", "bs", "uu", "SJ", "uw", "vM", "CD", "wF", "ZR", "ym", "mX", "UO", "jl", "Dw", "Qr", "Fs", "Yb", "Zy", "gf", "NJ", "rO", "cO", "np", "kA", "PI", "KE", "lT", "nN", "qk", "Ri", "ef", "od", "kr", "YU", "qT", "fY", "CL", "TK", "zs"}
    };*/
    
    public static String generate(){
        keys = charGenerate();
        String output = "";
        int y = getYear();
        int m = getMonth();
        int d = getDay();
        int s = r.nextInt(0, 9);
        output = keys[s][y];
        s = r.nextInt(0, 9);
        output = output + keys[s][m];
        s = r.nextInt(0, 9);
        output = output + keys[s][d];
        s = r.nextInt(0, 9);
        output = output + keys[s][s];
        s = r.nextInt(0, 9);
        output = output + keys[s][y];
        s = r.nextInt(0, 9);
        output = output + keys[s][m];
        s = r.nextInt(0, 9);
        output = output + keys[s][d];
        s = r.nextInt(0, 9);
        output = output + keys[s][s];

        return output;
    }
    
    private static int getYear(){
        int output = Integer.parseInt(("" + DateTools.getYear()).substring(2));
        return output;
    }
    
    private static int getMonth(){
        return DateTools.getDate(DateMethods.TODAY, 0, 0, 0).getMonth();
    }
    
    private static int getDay(){
        return DateTools.getDate(DateMethods.TODAY, 0, 0, 0).getDate();
    }
    
    public static String[][] charGenerate(){
        String a = "abcdefghijklmnopqrstuvxwyz"
                 + "ABCDEFGHIJKLMNOPQRSTUVXWYZ";
        String[][] output = new String[10][100];
        
        
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 100; j++){
                output[i][j] = "" + a.charAt(r.nextInt(a.length())) + a.charAt(r.nextInt(a.length()));
            }
        }        
        return output;
    }

}
