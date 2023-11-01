/**
 * <p>
 * 消息气泡
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-30 09:29
 */
import { MessageTypeEnum } from '#/constant/MessageTypeEnum'
import { Text } from './Text'
import { Unsupported } from './Unsupported'
import { Voice } from './Voice'

export interface MessageBubbleProps {
  // 消息类型
  msgType: MessageTypeEnum
}

// 定义消息类型组件的工厂函数
export function createMessageTypeComponent(msgType: MessageTypeEnum) {
  return <Text />
  switch (msgType) {
    case MessageTypeEnum.Text:
      return <Text />
    case MessageTypeEnum.Voice:
      return <Voice />
    // 添加其他消息类型的工厂函数
    // ...
    default:
      return <Unsupported />
  }
}
export function MessageBubble(props: MessageBubbleProps) {
  return createMessageTypeComponent(props.msgType)
}
