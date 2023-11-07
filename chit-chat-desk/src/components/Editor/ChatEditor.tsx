/**
 * <p>
 * 聊天消息编辑器
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 10:39
 */
import * as React from 'react'
import { ChatEditorContext } from './editor-context'
import { editorSubjectInstance } from './subject'
import { SubjectType } from './subject-type'

export interface ChatEditorEvents {}

export interface ChatEditorProps extends ChatEditorEvents {}

export const ChatEditor = React.memo<ChatEditorProps>(function ChatEditor(
  props
) {
  const editorRef = React.createRef<HTMLDivElement>()
  const [editorValue, setEditorValue] = React.useState('')

  function editorChanged(event: React.FormEvent<HTMLDivElement>) {
    if (editorRef.current == void 0) return
    editorSubjectInstance.notifyObservers({
      type: SubjectType.EDITOR_INPUT_CHANGE,
      data: event,
    })

    setEditorValue(event.currentTarget.innerText)
  }

  function onEditorKeyDown(event: React.KeyboardEvent<HTMLDivElement>) {
    if (editorRef.current == void 0) return
    editorSubjectInstance.notifyObservers({
      type: SubjectType.EDITOR_KEY_DOWN,
      data: event,
    })
  }

  return (
    <ChatEditorContext.Provider
      value={{
        editor: editorRef,
      }}>
      <div
        onKeyDown={onEditorKeyDown}
        ref={editorRef}
        contentEditable
        className="px-4 h-full w-full max-h-full overflow-auto cursor-auto outline-none"
        spellCheck={false}
        onInput={editorChanged}
      />
    </ChatEditorContext.Provider>
  )
})
