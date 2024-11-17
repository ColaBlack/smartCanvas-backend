package cn.cola.smartcanvas.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用删除请求(按id删除)
 *
 * @author ColaBlack
 */
@Data
public class DeleteRequest implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
}