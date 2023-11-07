/**
 * <p>
 * 编辑器钩子
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 11:31
 */
import React from 'react'
import { ChatEditorContext } from '../editor-context'

export function useEditor() {
  const { editor } = React.useContext(ChatEditorContext)
}
