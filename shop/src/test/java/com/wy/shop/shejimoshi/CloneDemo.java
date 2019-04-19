package com.wy.shop.shejimoshi;

import com.wy.shop.id.IdGenUtils;
import com.wy.shop.util.WriterHelper;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/4/19
 * @time 9:41
 */
public class CloneDemo {


    public static void main(String[] args) throws CloneNotSupportedException {
        Student mayun = new Student(IdGenUtils.get().getStrCodingByPrefix("HSD"), "马云");
        int i = mayun.hashCode();

        WriterHelper.writeObjInfo(mayun);
        WriterHelper.writeObjInfo(i);
        Object clone = mayun.clone();
        int i1 = clone.hashCode();

        WriterHelper.writeObjInfo(clone);
        WriterHelper.writeObjInfo(i1);

    }

}
