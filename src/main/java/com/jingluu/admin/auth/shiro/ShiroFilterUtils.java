package com.jingluu.admin.auth.shiro;

import org.apache.shiro.web.filter.mgt.DefaultFilter;

import java.util.List;

public class ShiroFilterUtils {

    public final static String FILTER_NAME_ANON = DefaultFilter.anon.name();
    public final static String FILTER_NAME_AUTHC = DefaultFilter.authc.name();
    public final static String FILTER_NAME_ROLES = DefaultFilter.roles.name();
    public final static String FILTER_NAME_PERMS = DefaultFilter.perms.name();

    public static String FORMAT_ROLES = FILTER_NAME_ROLES.concat("[%s]");
    public static String FORMAT_PERMS = FILTER_NAME_PERMS.concat("[%s]");


    public static int CREDENTIAL_HASH_ITERATIONS = 2;
    public static String ENCRYPTION_ALGORITHM_HASH_MD5 ="MD5";

    public static String formatRoles(List<String> roles){
        String authcRoles = null;
        if (roles != null && !roles.isEmpty()) {
            authcRoles= "";
            String roleFormat = "";
            for (String role : roles) {
                roleFormat += "," + role;
            }
            //roles[role,role,...]
            authcRoles = String.format(ShiroFilterUtils.FORMAT_ROLES, roleFormat.replaceFirst(",", ""));
        }
        return authcRoles;
    }

    public static String formatPerms(List<String> perms){
        String formatPerm = null;
        if (perms != null && !perms.isEmpty()) {
            formatPerm = "";
            for (String permission : perms) {
                formatPerm += ","+ permission;
            }
            //perms[perm1,perm2,...]
            formatPerm = String.format(ShiroFilterUtils.FORMAT_PERMS, formatPerm.replaceFirst(",", ""));
        }
        return formatPerm;
    }


}
