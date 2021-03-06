package net.airgame.bukkit.api.util;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 字符串工具
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class StringUtils {

    private StringUtils() {
    }

    /**
     * 替换颜色代码
     *
     * @param string 要替换的字符串
     * @return 替换后的字符串
     */
    @Nullable
    public static String replaceColorCode(@Nullable String string) {
        if (string == null) return null;
        return string.replace("&", "§");
    }

    /**
     * 替换颜色代码
     * <p>
     * 添加这个方法是因为 ConfigurationSection 中的 getString 方法有 @Nullable 注解
     * <p>
     * 导致 idea 会弹出某些警告，让人非常不爽
     *
     * @param string       要替换的字符串
     * @param defaultValue 若 string 为空则使用该字符串
     * @return 替换后的字符串
     */
    @NotNull
    public static String replaceColorCode(@Nullable String string, @NotNull String defaultValue) {
        if (string == null) {
            return replaceColorCode(defaultValue);
        }
        return replaceColorCode(string);
    }

    /**
     * 替换颜色代码
     *
     * @param strings 要替换的字符串
     * @return 替换后的字符串
     */
    @NotNull
    public static ArrayList<String> replaceColorCode(@Nullable Iterable<String> strings) {
        ArrayList<String> list = new ArrayList<>();
        if (strings == null) return list;
        for (String s : strings) {
            list.add(replaceColorCode(s));
        }
        return list;
    }

    /**
     * 替换颜色代码
     *
     * @param strings 要替换的字符串
     * @return 替换后的字符串
     */
    @NotNull
    public static ArrayList<String> replaceColorCode(@Nullable String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        if (strings == null) return list;
        for (String s : strings) {
            list.add(replaceColorCode(s));
        }
        return list;
    }

    @NotNull
    public static String[] replaceStringList(@NotNull String[] strings, @NotNull String key, @NotNull String value) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replace(key, value);
        }
        return strings;
    }

    @NotNull
    public static List<String> replaceStringList(@NotNull Iterable<String> strings, @NotNull String key, @NotNull String value) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : strings) {
            list.add(s.replace(key, value));
        }
        return list;
    }

    @NotNull
    public static List<String> replaceStringList(@NotNull List<String> strings, @NotNull String key, @NotNull String value) {
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, strings.get(i).replace(key, value));
        }
        return strings;
    }

    public static boolean startsWithIgnoreCase(@NotNull String string, @NotNull String start) {
        return string.toLowerCase().startsWith(start.toLowerCase());
    }

    public static boolean endsWithIgnoreCase(@NotNull String string, @NotNull String end) {
        return string.toLowerCase().endsWith(end.toLowerCase());
    }

    @NotNull
    public static ArrayList<String> startsWith(@NotNull Iterable<String> strings, @NotNull String start) {
        ArrayList<String> list = new ArrayList<>();
        for (String string : strings) {
            if (string.startsWith(start)) {
                list.add(string);
            }
        }
        return list;
    }

    @NotNull
    public static ArrayList<String> endsWith(@NotNull Iterable<String> strings, @NotNull String end) {
        ArrayList<String> list = new ArrayList<>();
        for (String string : strings) {
            if (string.endsWith(end)) {
                list.add(string);
            }
        }
        return list;
    }

    @NotNull
    public static ArrayList<String> startsWithIgnoreCase(@NotNull Iterable<String> strings, @NotNull String start) {
        ArrayList<String> list = new ArrayList<>();
        for (String string : strings) {
            if (startsWithIgnoreCase(string, start)) {
                list.add(string);
            }
        }
        return list;
    }

    @NotNull
    public static ArrayList<String> endsWithIgnoreCase(@NotNull Iterable<String> strings, @NotNull String end) {
        ArrayList<String> list = new ArrayList<>();
        for (String string : strings) {
            if (endsWithIgnoreCase(string, end)) {
                list.add(string);
            }
        }
        return list;
    }

    @NotNull
    public static String join(@NotNull Object[] array, @NotNull String separator) {
        return join(array, separator, 0, array.length);
    }

    @NotNull
    public static String join(@NotNull Object[] array, @NotNull String separator, int startIndex, int endIndex) {
        if (endIndex - startIndex <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                builder.append(separator);
            }
            builder.append(array[i]);
        }
        return builder.toString();
    }

    @NotNull
    public static String join(@NotNull Iterable<?> iterable, @NotNull String separator) {
        return join(iterable.iterator(), separator);
    }

    @NotNull
    public static String join(@NotNull Iterator<?> iterator, @NotNull String separator) {
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return String.valueOf(first);
        }

        StringBuilder builder = new StringBuilder(256);
        if (first != null) {
            builder.append(first);
        }

        while (iterator.hasNext()) {
            builder.append(separator);
            builder.append(iterator.next());
        }

        return builder.toString();
    }

}
