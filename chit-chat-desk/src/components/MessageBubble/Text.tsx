/**
 * <p>
 * 文字消息
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-30 09:30
 */

export function Text() {
  let css = `
  after:content-[''] 
  after:absolute 
  after:left-[-20px]
  after:top-0
  after:h-[20px]
  after:w-[20px]
  after:border-solid 
  after:border-b-transparent
  after:border-t-red-400 
  after:border-r-cyan-500 
  after:border-l-amber-300  
  `
  return (
    <>
      <div
        className={`bg-blue-400/70 px-3 py-2 rounded-xl inline-block text-sm`}>
        <p>
          The whole problem with the world is that fools and fanatics are always
          so certain of themselves, but wiser people so full of doubts.
        </p>
      </div>
    </>
  )
}
