package cn.cola.smartcanvas.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色枚举
 *
 * @author ColaBlack
 */
@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    /**
     * 普通用户
     */
    USER("用户", "user"),

    /**
     * 管理员
     */
    ADMIN("管理员", "admin"),

    /**
     * 被封号
     */
    BAN("被封号", "ban");

    private final String text;

    private final String value;


    /**
     * 获取值列表
     *
     * @return 值列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value value值
     * @return 枚举类
     */
    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
