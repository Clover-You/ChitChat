/**
 * <p>
 * 消息展示列表
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-30 14:35
 */
import { MessageItem } from './MessageItem'

export function MessageList() {
  let nodeList = []
  for (let i = 0; i < 10; i++) {
    nodeList.push(
      <MessageItem
        key={i}
        isSelf={i % 3 === 1}
      />
    )
  }

  return (
    <>
      <div>{nodeList}</div>
    </>
  )
}
