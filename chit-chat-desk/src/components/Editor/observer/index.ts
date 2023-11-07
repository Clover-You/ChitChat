/**
 * <p>
 * 编辑器主题观察者
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 13:19
 */
import { SubjectType } from '../subject-type'
import { EditorInputEventObserverImpl } from './input'
import { EditorKeyDownObserverImpl } from './keydown'

export interface EditorEventsObserver<T = any> {
  /**
   * whether the current observer can process the SubjectType
   * @param type 主题类型
   */
  canHandle(type: SubjectType): boolean

  invoke(data: T): void
}

export const EditorInputEventObserver = EditorInputEventObserverImpl
export const EditorKeyDownObserver = EditorKeyDownObserverImpl
