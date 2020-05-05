package com.lian.myLambda.common.core.exception.assertion;


import com.lian.myLambda.common.core.constant.IResponseEnum;
import com.lian.myLambda.common.core.exception.ArgumentException;
import com.lian.myLambda.common.core.exception.BaseException;

import java.text.MessageFormat;

/**
 * <pre>
 *
 * </pre>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
public interface ArgumentExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new ArgumentException(this, args, msg, t);
    }

}
