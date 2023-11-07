/**
 * <p>
 * 编辑器上下文
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 11:26
 */

import * as React from 'react'

export interface IEditorContext {
  editor?: React.RefObject<HTMLDivElement>
}

export const ChatEditorContext = React.createContext<IEditorContext>({})
