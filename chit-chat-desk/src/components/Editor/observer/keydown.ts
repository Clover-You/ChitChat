/**
 * <p>
 * 编辑器键盘按下监听器
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 14:22
 */
import * as React from 'react'

import { EditorEventsObserver } from '.'
import { SubjectType } from '../subject-type'

type Callback = (e: React.KeyboardEvent<HTMLDivElement>) => void
export class EditorKeyDownObserverImpl
  implements EditorEventsObserver<React.KeyboardEvent<HTMLDivElement>>
{
  private _callback: Callback

  constructor(callback: Callback) {
    this._callback = callback
  }

  invoke(data: React.KeyboardEvent<HTMLDivElement>): void {
    this._callback(data)
  }

  canHandle(type: SubjectType): boolean {
    return type === SubjectType.EDITOR_KEY_DOWN
  }
}
