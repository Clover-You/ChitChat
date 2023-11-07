/**
 * <p>
 * 编辑器 API
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 11:36
 */
import * as React from 'react'
import { EditorInputEventObserver, EditorKeyDownObserver } from './observer'
import { editorSubjectInstance } from './subject'

type DidChangeTextDocument = (
  editorEvent: React.FormEvent<HTMLDivElement>
) => void

type EditorKeyDown = (keyEvent: React.KeyboardEvent<HTMLDivElement>) => void

class ChitChatEditor {
  private _onDidChangeTextDocument = new Set<DidChangeTextDocument>()

  private _onKeyDownHandlers = new Set<EditorKeyDown>()

  constructor() {
    const eventsObserver = new EditorInputEventObserver(
      this.onInpputEvent.bind(this)
    )
    editorSubjectInstance.registerObserver(eventsObserver)

    const keyDownObserver = new EditorKeyDownObserver(
      this.onEditorKeyDownInvoker.bind(this)
    )
    editorSubjectInstance.registerObserver(keyDownObserver)
  }

  private onInpputEvent(event: React.FormEvent<HTMLDivElement>) {
    this._onDidChangeTextDocument.forEach((callback) => callback(event))
  }

  private onEditorKeyDownInvoker(event: React.KeyboardEvent<HTMLDivElement>) {
    this._onKeyDownHandlers.forEach((callback) => callback(event))
  }

  onDidChangeTextDocument(callback: DidChangeTextDocument) {
    return this.register(callback, this._onDidChangeTextDocument)
  }

  onEditorKeyDown(callback: EditorKeyDown) {
    return this.register(callback, this._onKeyDownHandlers)
  }

  private register(fn: Function, set: Set<Function>) {
    if (set.has(fn)) {
      throw Error('callback already registered')
    }

    set.add(fn)

    return () => {
      set.delete(fn)
    }
  }
}

export const editor = new ChitChatEditor()
